package com.letop3.ktsh.model.entity.ennemies;

import com.letop3.ktsh.model.entity.Attackable;
import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Pathfinder;

import javafx.beans.value.ChangeListener;

public abstract class Ennemies extends Attackable {
    private static Pathfinder playerfinder = null;

    private Player player;
    private int detectionRange;
    private int atk;

    public Ennemies(Position position, Player player, int detectionRange, int hp) {
        super(position, player.getGround(), hp);
        this.player = player;
        this.detectionRange = detectionRange * (Chunk.CHUNK_SIZE / 11);
        this.atk = 1;

        if (playerfinder == null) {
            playerfinder = new Pathfinder(player.getPosition(), getPosition(), player.getGround());
        }

        ChangeListener<Number> playerListener = (observable, oldValue, newValue) -> {
            playerfinder.setTarget(player.getPosition(), getPosition());
        };

        player.getPosition().xProperty().addListener(playerListener);
        player.getPosition().yProperty().addListener(playerListener);
    }

    public void attack() {
        player.takeDamage(atk);
    }

    @Override
    public void update() {
        super.update();
        if (player.getPosition().distance(getPosition()) <= detectionRange) {
            Direction direction = playerfinder.getDirection(getPosition());
            setDirection(direction);
            if (playerfinder.isArrived()) attack();
        }
    }
}
