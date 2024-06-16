package com.letop3.ktsh.view.entity.NPC;

import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.npc.action.textAction.SelectTextAction;
import com.letop3.ktsh.model.entity.npc.action.textAction.TextAction;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class DialogueView {
    private GridPane dialogueBox;
    private Label dialogueText;
    private ListView<String> dialogueResponses;

    private SelectTextAction selectAction;

    public DialogueView(GridPane dialogueBox, Label dialogueText, ListView<String> dialogueResponses) {
        this.dialogueBox = dialogueBox;
        this.dialogueText = dialogueText;
        this.dialogueResponses = dialogueResponses;

        dialogueResponses.getSelectionModel().selectedItemProperty().addListener((obs, old, nouv) -> {
            int index = dialogueResponses.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                System.out.println("selection changed : " + index);
                selectAction.setSelection(index);
            }
        });
    }

    public void show(TextAction action, NPC target) {
        dialogueResponses.setVisible(false);
        dialogueResponses.getItems().clear();
        dialogueText.setText(action.getText());

        if (action instanceof SelectTextAction) {
            String[] options = ((SelectTextAction)action).getOptions(target);
            for (int i = 0; i < options.length; i++) {
                dialogueResponses.getItems().add((i + 1) + ": " + options[i]);
            }
            dialogueResponses.setVisible(true);

            selectAction = (SelectTextAction)action;
        }

        dialogueBox.setVisible(true);
    }

    public void hide() {
        dialogueBox.setVisible(false);
    }
}
