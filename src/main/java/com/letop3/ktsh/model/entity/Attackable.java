package com.letop3.ktsh.model.entity;

import com.letop3.ktsh.model.ground.Ground;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Attackable extends Entity {
    private final int maxHp;
    private IntegerProperty hp;

    public Attackable(Position position, Ground ground, int maxHp) {
        super(position, ground);
        
        this.maxHp = maxHp;
        this.hp = new SimpleIntegerProperty(maxHp);
    }

    public void takeDamage(int dmg) {
        this.hp.set(Math.max(this.hp.get() - dmg, 0));
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp.get();
    }

    public void setHp(int hp) {
        this.hp.set(hp);
    }

    public IntegerProperty hpProperty() {
        return hp;
    }
}
