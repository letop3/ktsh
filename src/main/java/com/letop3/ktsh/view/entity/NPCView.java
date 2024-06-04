package com.letop3.ktsh.view.entity;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.view.animation.AnimationAdapter;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class NPCView extends EntityView {
	private final NPC npc;
	private final Label interactLabel;	
	private final Player player;

	public NPCView(AnimationAdapter animationAdapter, Pane spriteTarget, Position screenPosition, NPC npc, Player player) {
		super(animationAdapter, spriteTarget, screenPosition);
		this.npc = npc;
		this.player = player;
		
		this.interactLabel = new Label("Interact");
		this.interactLabel.setTranslateX(0);
		this.interactLabel.setTranslateY(-20);
		this.interactLabel.setVisible(false);

		spriteTarget.getChildren().add(interactLabel);
	}

	@Override
	public void update() {
		super.update();
		if (npc.isInterractible(player)) {
			interactLabel.setVisible(true);
		}
		else {
			interactLabel.setVisible(false);
		}
	}
}
