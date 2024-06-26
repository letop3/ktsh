package com.letop3.ktsh.model.entity.player;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.model.entity.Attackable;
import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Interractible;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.ennemies.Enemy;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.item.artefact.BombMegumin;
import com.letop3.ktsh.model.item.arme.DulledSword;
import com.letop3.ktsh.model.item.arme.Excaliba;
import com.letop3.ktsh.model.item.arme.WornShield;
import com.letop3.ktsh.model.item.artefact.BotteErmS;
import com.letop3.ktsh.model.item.artefact.DinStaff;
import com.letop3.ktsh.model.item.artefact.FluteEnchantee;
import com.letop3.ktsh.model.item.artefact.GantHerkUl;
import com.letop3.ktsh.model.item.consomable.PotionAtk;
import com.letop3.ktsh.model.item.consomable.PotionHP;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import java.util.Timer;
import java.util.TimerTask;

public class Player extends Attackable {
    private final Stuff stuff;
    private final BooleanProperty lock;
    private int atk;
    private Interractible interractionTarget;
    private PlayerListener pL;
    private boolean attackOnCD = false;
    private final BooleanProperty enAtq;
    private final Env env;

    public Player(Position position, Ground ground, Env env) {
        super(position, ground, 12);
        this.atk = 1;

        interractionTarget = null;

        this.stuff = new Stuff();
        stuff.addItem(new DulledSword(1, "Sword Test", "Un test pour arme", 100));
        stuff.addItem(new Excaliba(2, "Excalibur", "Un test pour arme", 100));
        stuff.addItem(new WornShield(1, "Shield Test", "Un test pour bouclier", 100));
        stuff.addItem(new PotionHP(1, "Potion Test Conso", "Un test pour conso", 100));
        stuff.addItem(new PotionAtk(1, "Potion atk", "test", 100));
        stuff.addItem(new BotteErmS(1, "Bottes Dash", "Test", 100));
        stuff.addItem(new DinStaff(1,"Din Staff", "Test", 100));
        stuff.addItem(new GantHerkUl(1,"Gant Herk Ul", "Test", 100));
        stuff.addItem(new BombMegumin(1,"Bomb Megumin", "Test", 100));
        stuff.addItem(new FluteEnchantee(1, "Flute Enchantée", "Test", 100));
        this.lock = new SimpleBooleanProperty(false);
        this.enAtq = new SimpleBooleanProperty(false);

        this.env = env;
        this.pL = null;
    }

    public Env getEnv() {
        return env;
    }

    public void setEnAtq(boolean enAtq) {
        this.enAtq.set(enAtq);
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

    public int[] getHearts() {
        int fullHearts = getHp() / 2;
        int halfHearts = getHp() % 2;
        int totalHearts = getMaxHp() / 2;
        return new int[]{fullHearts, halfHearts, totalHearts};
    }

    @Override
    public String toString() {
        return "Player";
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
    public void update(long frame) {

        if (!lock.get() && !enAtq.get()) {
            super.update(frame);
            double minDistance = Double.MAX_VALUE;
            for (Chunk[] chunks : getGround().getChunks()) {
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

    public void useQuickSlot() {
        if ((!lock.get() && !enAtq.get()) && this.stuff.getQuickSlot() != null) {
            this.stuff.getQuickSlot().action(this);
        }
    }

    public void attack(Env env) {
        if ((!lock.get() && !enAtq.get()) && !attackOnCD) {
            attackOnCD = true;
            setEnAtq(true);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        attackOnCD = false;
                        setEnAtq(false);
                    });
                }
            }, 400);

            if (pL != null) {
                pL.onAttack();

                Bounds attackArea = null;

                switch (getLastDirection()) {
                    case NORTH, NORTH_EAST, NORTH_WEST:
                        attackArea = new BoundingBox(this.getPosition().getX() - 8, this.getPosition().getY() - 24, 48, 24);
                        break;
                    case SOUTH, SOUTH_EAST, SOUTH_WEST:
                        attackArea = new BoundingBox(this.getPosition().getX() - 8, this.getPosition().getY() + 32, 48, 24);
                        break;
                    case EAST:
                        attackArea = new BoundingBox(this.getPosition().getX() + 30, this.getPosition().getY() - 8, 24, 48);
                        break;
                    case WEST:
                        attackArea = new BoundingBox(this.getPosition().getX() - 20, this.getPosition().getY() - 8, 24, 48);
                        break;
                }

                for (Chunk chunk : env.getGround().getCurrentChunks()) {
                    for (Entity entity : chunk.getEntities()) {
                        if (entity instanceof Enemy) {
                            if (attackArea != null && attackArea.intersects(entity.getHitbox())) {
                                Enemy ennemie = (Enemy)entity;
                                ennemie.takeDamage(this.atk);
                                System.out.println(ennemie.getHp());
                            }
                        }
                    }
                }
            }
        }
    }
}


