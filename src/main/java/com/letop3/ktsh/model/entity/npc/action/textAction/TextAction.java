package com.letop3.ktsh.model.entity.npc.action.textAction;

import com.letop3.ktsh.model.entity.npc.action.Action;

public abstract class TextAction extends Action {
	private String text;

	public TextAction(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
