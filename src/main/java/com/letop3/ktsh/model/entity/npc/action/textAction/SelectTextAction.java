package com.letop3.ktsh.model.entity.npc.action.textAction;

import com.letop3.ktsh.model.entity.npc.NPC;

public abstract class SelectTextAction extends TextAction {
    private int selection;

    public SelectTextAction(String text) {
        super(text);
        selection = 0;
    }

    public abstract String[] getOptions(NPC target);

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }
}
