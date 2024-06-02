package com.letop3.ktsh.model.entity.player;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.item.arme.Sword;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player extends Entity {
    private IntegerProperty hp;
    private int maxHp;
    private Stuff stuff;

    public Player(Position position) {
        super(position);
        this.maxHp = 12;
        this.hp = new SimpleIntegerProperty(this.maxHp);
        this.stuff = new Stuff();
        stuff.addItem(new Sword(1,"sword","test",100));
        stuff.addItem(new Sword(2,"sword","test",100));
        stuff.addItem(new Sword(3,"sword","test",100));
        stuff.addItem(new Sword(4,"sword","test",100));
        stuff.addItem(new Sword(5,"sword","test",100));
        stuff.addItem(new Sword(6,"sword","test",100));
        stuff.addItem(new Sword(7,"sword","test",100));
        stuff.addItem(new Sword(8,"sword","test",100));
        stuff.addItem(new Sword(9,"sword","test",100));
        stuff.addItem(new Sword(10,"sword","test",100));
        stuff.addItem(new Sword(11,"sword","test",100));
        stuff.addItem(new Sword(12,"sword","test",100));

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
}

