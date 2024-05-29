package com.letop3.ktsh.model.entity.npc;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;

public class Npc extends Entity implements Interractible {
    private final Action action;

    public Npc(Position position, Action action) {
        super(position);
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public boolean isInterractible() {
        return false;
    }

    @Override
    public void interract() {
        //TODO: Faire une action a l'aide de l'objet Action
    }

    @Override
    public String toString() {
        return "Npc";
    }
}
