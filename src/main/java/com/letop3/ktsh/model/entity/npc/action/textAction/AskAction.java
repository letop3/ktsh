package com.letop3.ktsh.model.entity.npc.action.textAction;

import java.util.List;

import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.npc.action.Action;

public class AskAction extends SelectTextAction {
    private List<Action> nextAction;
    private String[] options;
    
    public AskAction(String text, String[] options, List<Action> nextAction) {
        super(text);
        this.options = options;
        this.nextAction = nextAction;
    }

    @Override
    public String[] getOptions(NPC target) {
        return options;
    }

    @Override
    public Action getNextAction() {
        return nextAction.get(getSelection());
    }

    @Override
    public void execute(NPC target) {
        
    }
}
