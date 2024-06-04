package com.letop3.ktsh.model.entity.npc;

import com.letop3.ktsh.model.entity.Interractible;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.npc.dialogue.Dialogue;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Ground;

public class NPC extends Interractible {
    private final Dialogue dialogue;

    public NPC(Position position, Ground ground, Dialogue dialogue) {
        super(position, ground);
        this.dialogue = dialogue;
    }

    public Dialogue getDialogue() {
        return dialogue;
    }

    @Override
    public boolean isInterractible(Player player) {
		return super.isInterractible(player) && dialogue != null;
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
