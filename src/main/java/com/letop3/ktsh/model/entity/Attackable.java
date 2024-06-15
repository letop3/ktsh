package com.letop3.ktsh.model.entity;

import com.letop3.ktsh.model.ground.Ground;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Attackable extends Entity {
	private IntegerProperty hp;

	public Attackable(Position position, Ground ground, int hp) {
		super(position, ground);
		
		this.hp = new SimpleIntegerProperty(hp);
	}

	public void takeDamage(int dmg) {
        this.hp.set(Math.max(this.hp.get() - dmg, 0));
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
