package com.letop3.ktsh.model.entity.ennemies.mobs;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.ennemies.Enemy;
import com.letop3.ktsh.model.entity.ennemies.ennemieActions.AttackAction;
import com.letop3.ktsh.model.entity.player.Player;

public class Knight extends Enemy {
    private boolean controlled;

    public Knight(Position position, Player player) {
        super(position, player, 5, 3);
        this.controlled = false;
    }

    protected Knight(Position position, Player player, int health, int damage) {
        super(position, player, health, damage);
        this.controlled = false;
    }

    public void setControlled() {
        this.controlled = true;
    }

    @Override
    public void update(long frame) {
        if (!controlled && getAction() == null) setAction(new AttackAction(this));
        super.update(frame);
        controlled = false;
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
