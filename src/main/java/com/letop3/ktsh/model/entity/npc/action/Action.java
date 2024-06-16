package com.letop3.ktsh.model.entity.npc.action;

import com.letop3.ktsh.model.entity.npc.NPC;

public interface Action {
    public Action getNextAction();
    public void execute(NPC target);
}
