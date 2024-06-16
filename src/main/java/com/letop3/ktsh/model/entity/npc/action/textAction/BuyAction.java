package com.letop3.ktsh.model.entity.npc.action.textAction;

import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.npc.action.Action;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.item.Item;
import com.letop3.ktsh.model.item.artefact.Artefact;

public class BuyAction extends SelectTextAction {
    private enum TransactionState {
        OK,
        NO_MONEY,
        INVALID_ITEM,
    }

    private TransactionState transactionResult;
    private Action nextAction;
    private Player player;

    public BuyAction(String text, Player player, Action nextAction) {
        super(text);
        this.nextAction = nextAction;
        this.player = player;
        transactionResult = TransactionState.OK;
    }

    @Override
    public String[] getOptions(NPC target) {
        String options[] = new String[player.getStuff().getInventaire().size()];
        for (int i = 0; i < player.getStuff().getInventaire().size(); i++) {
            options[i] = player.getStuff().getInventaire().get(i).getNom();
        }
        return options;
    }

    @Override
    public Action getNextAction() {
        return switch (transactionResult) {
            case NO_MONEY -> new SpeakAction("Je n'ai pas assez d'argent pour acheter cet objet.", this);
            case INVALID_ITEM -> new SpeakAction("Je ne peux pas acheter cet objet.", this);
            default -> nextAction;
        };
    }

    @Override
    public void execute(NPC target) {
        Item selectedItem = player.getStuff().getInventaire().get(getSelection());

        if (selectedItem instanceof Artefact) {
            transactionResult = TransactionState.INVALID_ITEM;
        }
        else if (target.getStuff().getMoney() < selectedItem.getPrix()) {
            transactionResult = TransactionState.NO_MONEY;
        }
        else {
            target.getStuff().getInventaire().add(selectedItem);
            player.getStuff().getInventaire().remove(selectedItem);
            player.getStuff().addMoney(selectedItem.getPrix());
        }
    }
}
