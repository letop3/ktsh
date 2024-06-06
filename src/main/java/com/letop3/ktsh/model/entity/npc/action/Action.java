package com.letop3.ktsh.model.entity.npc.action;

import com.letop3.ktsh.model.entity.npc.NPC;

public abstract class Action {
    public abstract Action getNextAction();
    public abstract void execute(NPC target);
}
