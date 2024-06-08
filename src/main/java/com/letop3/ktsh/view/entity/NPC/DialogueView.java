package com.letop3.ktsh.view.entity.NPC;

import com.letop3.ktsh.model.entity.npc.action.textAction.AskAction;
import com.letop3.ktsh.model.entity.npc.action.textAction.TextAction;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class DialogueView {
    private GridPane dialogueBox;
    private Label dialogueText;
    private ListView<String> dialogueResponses;

    public DialogueView(GridPane dialogueBox, Label dialogueText, ListView<String> dialogueResponses) {
        this.dialogueBox = dialogueBox;
        this.dialogueText = dialogueText;
        this.dialogueResponses = dialogueResponses;
    }

    public void show(TextAction action) {
        dialogueResponses.setVisible(false);
        dialogueResponses.getItems().clear();
        dialogueText.setText(action.getText());

        if (action instanceof AskAction) {
            String[] options = ((AskAction)action).getOptions();
            for (int i = 0; i < options.length; i++) {
                dialogueResponses.getItems().add((i + 1) + ": " + options[i]);
            }
            dialogueResponses.setVisible(true);

            dialogueResponses.getSelectionModel().selectedItemProperty().addListener((obs, old, nouv) -> {
                int index = dialogueResponses.getSelectionModel().getSelectedIndex();
                if (index != -1) {
                    System.out.println("selection changed : " + index);
                    ((AskAction)action).setSelection(index);
                }
            });
        }

        dialogueBox.setVisible(true);
    }

    public void hide() {
        dialogueBox.setVisible(false);
    }
}
