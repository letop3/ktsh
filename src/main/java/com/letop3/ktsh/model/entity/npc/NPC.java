package com.letop3.ktsh.model.entity.npc;

import com.letop3.ktsh.model.entity.Interractible;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.npc.action.Action;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Ground;

public class NPC extends Interractible {
    private NPCInterractListener interractListener;
    private final Action action;

    public NPC(Position position, Ground ground, Action action) {
        super(position, ground);
        this.action = action;
        interractListener = null;
    }

    public Action getAction() {
        return action;
    }

    public void setInterractListener(NPCInterractListener interractListener) {
        this.interractListener = interractListener;
    }

    @Override
    public boolean isInterractible(Player player) {
        return super.isInterractible(player) && action != null;
    }

    @Override
    public void interract() {
        if (interractListener != null) interractListener.interract();
    }

    @Override
    public String toString() {
        return "Npc";
    }
}
