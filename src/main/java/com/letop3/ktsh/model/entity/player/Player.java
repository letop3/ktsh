package com.letop3.ktsh.model.entity.player;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Interractible;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.item.arme.Shield;
import com.letop3.ktsh.model.item.arme.Sword;
import com.letop3.ktsh.model.item.consomable.PotionHP;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player extends Entity {
    private IntegerProperty hp;
    private int maxHp;
    private Stuff stuff;

    private Interractible interractionTarget;

    public Player(Position position, Ground ground) {
        super(position, ground);
        interractionTarget = null;
        this.maxHp = 12;
        this.hp = new SimpleIntegerProperty(this.maxHp);
        this.stuff = new Stuff();
        stuff.addItem(new Sword(1, "Sword Test", "Un test pour arme", 100));
        stuff.addItem(new Sword(2, "Excalibur", "Un test pour arme", 100));
        stuff.addItem(new Shield(1, "Shield Test", "Un test pour bouclier", 100));
        stuff.addItem(new PotionHP(1, "Potion Test Conso", "Un test pour conso", 100));
    }

    public Interractible getInterractionTarget() {
        return interractionTarget;
    }

    public void interract() {
        if (interractionTarget != null) {
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

    @Override
    public void update() {
        super.update();

        double minDistance = Double.MAX_VALUE;
        for (Chunk chunks[] : ground.getChunks()) {
            for (Chunk chunk : chunks) {
                for (Entity entity : chunk.getEntities()) {
                    if (entity instanceof Interractible && ((Interractible)entity).isInterractible(this) && entity.getPosition().distance(super.getPosition()) < minDistance) {
                        minDistance = entity.getPosition().distance(super.getPosition());
                        interractionTarget = (Interractible)entity;
                    }
                }
            }
        }
    }
}

