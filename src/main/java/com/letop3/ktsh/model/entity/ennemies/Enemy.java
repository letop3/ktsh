package com.letop3.ktsh.model.entity.ennemies;

import java.util.Timer;
import java.util.TimerTask;

import com.letop3.ktsh.model.entity.Attackable;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.ennemies.ennemieActions.EnemyAction;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;

public abstract class Enemy extends Attackable {
    private Player player;
    private int detectionRange;
    private int atk;

    public long cooldown;
    private boolean atkCooldown;

    private EnemyState state;
    private EnemyAction action;

    public Enemy(Position position, Player player, int detectionRange, int hp) {
        super(position, player.getGround(), hp);
        this.player = player;
        this.detectionRange = detectionRange * (Chunk.CHUNK_SIZE / 11);
        this.atk = 1;

        this.cooldown = 1;
        this.atkCooldown = false;

        this.state = EnemyState.NORMAL;
        this.action = null;
    }

    public void attack() {
        if (!atkCooldown) {
            player.takeDamage(atk);

            atkCooldown = true;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    atkCooldown = false;
                }
            }, cooldown * 1000);
        }
    }

    public boolean inCooldown() {
        return atkCooldown;
    }

    public Player getPlayer() {
        return player;
    }

    public EnemyState getState() {
        return state;
    }

    public void setAction(EnemyAction action) {
        this.action = action;
    }

    public EnemyAction getAction() {
        return action;
    }

    @Override
    public void update(long frame) {
        super.update(frame);

        double distance = player.getPosition().distance(getPosition());
        if (distance <= detectionRange) {
            if (action != null) action.execute();
            
            // state update
            if (distance < (Chunk.CHUNK_SIZE / 11) * 2 && atkCooldown) {
                state = EnemyState.ENDENGERED;
            }
            else if (distance < (Chunk.CHUNK_SIZE / 11) * 2 && getHp() < getMaxHp() / 4) {
                state = EnemyState.FREIGHTENED;
            }
            else {
                state = EnemyState.NORMAL;
            }
        }
    }
}
