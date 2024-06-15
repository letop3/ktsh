package com.letop3.ktsh.model.entity.npc.action.textAction;

import java.util.List;

import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.npc.action.Action;

public class AskAction extends TextAction {
    private List<Action> nextAction;
    private int selection;
    private String[] options;

    public AskAction(String text, String[] options, List<Action> nextAction) {
        super(text);
        this.options = options;
        this.nextAction = nextAction;
        selection = 0;
    }

    public String[] getOptions() {
        return options;
    }

    public void setSelection(int selection) {
        if (selection < 0 || selection >= options.length) this.selection = 0;
        else this.selection = selection;
    }

    @Override
    public Action getNextAction() {
        return nextAction.get(selection);
    }

    @Override
    public void execute(NPC target) {
        
    }
}
