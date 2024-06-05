package com.letop3.ktsh.model.entity.npc.action.textAction;

import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.npc.action.Action;

public class SpeakAction extends TextAction {
	private Action nextAction;

	public SpeakAction(String text, Action nextAction) {
		super(text);
		this.nextAction = nextAction;
	}

	@Override
	public Action getNextAction() {
		return nextAction;
	}

	@Override
	public void execute(NPC target) {
		
	}
}
