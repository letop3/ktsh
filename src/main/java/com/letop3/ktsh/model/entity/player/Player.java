package com.letop3.ktsh.model.entity.player;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Interractible;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.ennemies.Ennemies;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.item.arme.*;
import com.letop3.ktsh.model.item.artefact.BotteErmS;
import com.letop3.ktsh.model.item.consomable.PotionAtk;
import com.letop3.ktsh.model.item.consomable.PotionHP;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;

import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity {
    private int maxHp;
    private Stuff stuff;
    private BooleanProperty lock;
    private int atk;
    private Interractible interractionTarget;
    private PlayerListener pL;
    private boolean attackOnCD = false;

    public Player(Position position, Ground ground) {
        super(position, ground);
        interractionTarget = null;
        this.maxHp = 12;
        this.atk = 1;
        this.hp = new SimpleIntegerProperty(this.maxHp);
        this.stuff = new Stuff();
        stuff.addItem(new DulledSword(1, "Sword Test", "Un test pour arme", 100));
        stuff.addItem(new Excaliba(2, "Excalibur", "Un test pour arme", 100));
        stuff.addItem(new WornShield(1, "Shield Test", "Un test pour bouclier", 100));
        stuff.addItem(new PotionHP(1, "Potion Test Conso", "Un test pour conso", 100));
        stuff.addItem(new PotionAtk(1, "Potion atk", "test", 100));
        stuff.addItem(new BotteErmS(1, "Bottes Dash", "Test", 100));
        this.lock = new SimpleBooleanProperty(false);

        this.pL = null;
    }

    public void setPL(PlayerListener pL) {
        this.pL = pL;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public Interractible getInterractionTarget() {
        return interractionTarget;
    }

    public void interract() {
        if (interractionTarget != null && interractionTarget.isInterractible(this)) {
            interractionTarget.interract();
        }
    }

    public int getHp() {
        return hp.get();
    }

    public void setHp(int hp) {
        this.hp.setValue(hp);
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int[] getHearts() {
        int fullHearts = hp.get() / 2;
        int halfHearts = hp.get() % 2;
        int totalHearts = maxHp / 2;
        return new int[]{fullHearts, halfHearts, totalHearts};
    }

    @Override
    public String toString() {
        return "Player";
    }

    public IntegerProperty hpProperty() {
        return hp;
    }

    public Stuff getStuff() {
        return stuff;
    }

    public boolean isLock() {
        return lock.get();
    }

    public BooleanProperty lockProperty() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock.set(lock);
    }

    @Override
    public void update() {
        if (!lock.get()) {
            super.update();

            double minDistance = Double.MAX_VALUE;
            for (Chunk chunks[] : getGround().getChunks()) {
                for (Chunk chunk : chunks) {
                    for (Entity entity : chunk.getEntities()) {
                        if (entity instanceof Interractible && ((Interractible) entity).isInterractible(this) && entity.getPosition().distance(super.getPosition()) < minDistance) {
                            minDistance = entity.getPosition().distance(super.getPosition());
                            interractionTarget = (Interractible) entity;
                        }
                    }
                }
            }
        }
    }

    @Override
    public Rectangle2D getHitbox() {
        return null;
    }

    public void useQuickSlot() {
        if (!lock.get() && this.stuff.getQuickSlot() != null) {
            this.stuff.getQuickSlot().action(this);
        }
    }

    public void attack(Env env) {
        if (!lock.get() && !attackOnCD) {
            attackOnCD = true;

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> attackOnCD = false);
                }
            }, 400);

            if (pL != null) {
                pL.onAttack();

                Rectangle2D attackArea = null;

                switch (getLastDirection()) {
                    case NORTH:
                        attackArea = new Rectangle2D(this.getPosition().getX() - 8, this.getPosition().getY() - 24, 48, 24);
                        break;
                    case SOUTH:
                        attackArea = new Rectangle2D(this.getPosition().getX() - 8, this.getPosition().getY() + 32, 48, 24);
                        break;
                    case EAST:
                        attackArea = new Rectangle2D(this.getPosition().getX() + 30, this.getPosition().getY() - 8, 24, 48);
                        break;
                    case WEST:
                        attackArea = new Rectangle2D(this.getPosition().getX() - 20, this.getPosition().getY() - 8, 24, 48);
                        break;
                }

                for (Entity entity : env.getGround().getCurrentChunk().getEntities()) {
                    if (entity instanceof Ennemies) {
                        assert attackArea != null;
                        if (attackArea.intersects(entity.getHitbox())) {
                            ((Ennemies) entity).takeDamage(this.atk);
                            System.out.println("Mob HP : " + entity.getHp());
                        }
                    }
                }
            }
        }
    }
}


