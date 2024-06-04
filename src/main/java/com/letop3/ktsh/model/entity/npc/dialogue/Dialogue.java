package com.letop3.ktsh.model.entity.npc.dialogue;

public abstract interface Dialogue {
    public Dialogue nextStep();
    public Action getAction();
    public void setSelection(int selection);
}
