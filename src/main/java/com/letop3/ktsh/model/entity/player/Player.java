package com.letop3.ktsh.model.entity.player;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.item.arme.Shield;
import com.letop3.ktsh.model.item.arme.Sword;
import com.letop3.ktsh.model.item.arme.*;
import com.letop3.ktsh.model.item.artefact.BotteErmS;
import com.letop3.ktsh.model.item.consomable.PotionAtk;
import com.letop3.ktsh.model.item.consomable.PotionHP;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player extends Entity {
    private IntegerProperty hp;
    private int maxHp;
    private Stuff stuff;
    private BooleanProperty lock;
    private int atk;

    public Player(Position position) {
        super(position);
        this.maxHp = 12;
        this.atk = 1;
        this.hp = new SimpleIntegerProperty(this.maxHp);
        this.stuff = new Stuff();
        stuff.addItem(new DulledSword(1, "Sword Test", "Un test pour arme", 100));
        stuff.addItem(new Excaliba(2, "Excalibur", "Un test pour arme", 100));
        stuff.addItem(new WornShield(1, "Shield Test", "Un test pour bouclier", 100));
        stuff.addItem(new PotionHP(1, "Potion Test Conso", "Un test pour conso", 100));
        stuff.addItem(new PotionAtk(1, "Potion atk","test",100));
        stuff.addItem(new BotteErmS(1,"Bottes Dash", "Test", 100));
        this.lock = new SimpleBooleanProperty(false);
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
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
    public void update(Ground ground) {
        if (!lock.get()){
            super.update(ground);
        }
    }

    public void useQuickSlot() {
        if (!lock.get() && this.stuff.getQuickSlot() != null) {
            this.stuff.getQuickSlot().action(this);
        }
    }
}

