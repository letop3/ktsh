package com.letop3.ktsh.view.entity;

import java.util.Scanner;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.npc.NPCInterractListener;
import com.letop3.ktsh.model.entity.npc.action.Action;
import com.letop3.ktsh.model.entity.npc.action.textAction.AskAction;
import com.letop3.ktsh.model.entity.npc.action.textAction.TextAction;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.view.animation.AnimationAdapter;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class NPCView extends EntityView {
	private final NPC npc;
	private final Label interactLabel;	
	private final Player player;

	private Action action;

	public NPCView(AnimationAdapter animationAdapter, Pane spriteTarget, Position screenPosition, NPC npc, Player player) {
		super(animationAdapter, spriteTarget, screenPosition);
		this.npc = npc;
		this.player = player;
		
		this.interactLabel = new Label("Interact");
		this.interactLabel.setTranslateX(0);
		this.interactLabel.setTranslateY(-20);
		this.interactLabel.setVisible(false);
		spriteTarget.getChildren().add(interactLabel);

		action = npc.getAction();
		npc.setInterractListener(new NPCInterractListener() {
			private final Scanner scanner = new Scanner(System.in);

			@Override
			public void interract() {
				if (action instanceof TextAction) {
					System.out.println(((TextAction)action).getText());
					if (action instanceof AskAction) {
						String[] options = ((AskAction)action).getOptions();
						for (int i = 0; i < options.length; i++) {
							System.out.println((i + 1) + ": " + options[i]);
						}
						((AskAction)action).setSelection(scanner.nextInt() - 1);
					}
				}
				else {
					action.execute(npc);
				}

				action = action.getNextAction();
				if (action == null) {
					action = npc.getAction();
				}
			}
		});
	}

	@Override
	public void update() {
		super.update();
		if (player.getInterractionTarget() == npc && npc.isInterractible(player)) {
			interactLabel.setVisible(true);
		}
		else {
			interactLabel.setVisible(false);
		}
	}
}
