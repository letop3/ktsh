package com.letop3.ktsh.view.entity.NPC;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.npc.NPCInterractListener;
import com.letop3.ktsh.model.entity.npc.action.Action;
import com.letop3.ktsh.model.entity.npc.action.textAction.TextAction;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.view.animation.AnimationAdapter;
import com.letop3.ktsh.view.entity.EntityView;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class NPCView extends EntityView {
    private final NPC npc;
    private final Label interactLabel;    
    private final Player player;

    private Action action;

    public NPCView(AnimationAdapter animationAdapter, Pane spriteTarget, Position screenPosition, NPC npc, Player player, DialogueView dialogueView) {
        super(animationAdapter, spriteTarget, screenPosition);
        this.npc = npc;
        this.player = player;
        
        this.interactLabel = new Label("Interact");
        this.interactLabel.setTranslateX(0);
        this.interactLabel.setTranslateY(-20);
        this.interactLabel.setVisible(false);
        spriteTarget.getChildren().add(interactLabel);

        npc.setInterractListener(new NPCInterractListener() {
            @Override
            public void onInterract() {
                if (action == null) {
                    dialogueView.hide();
                    action = npc.getDialogue();
                    interactLabel.setText("Next");
                }
                else action = action.getNextAction();

                if (action != null) {
                    if (action instanceof TextAction) {
                        dialogueView.show((TextAction) action);
                    } else {
                        action.execute(npc);
                    }
                }
                else {
                    interactLabel.setText("Interact");
                    dialogueView.hide();
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
