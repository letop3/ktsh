package com.letop3.ktsh.model.entity.npc.action.textAction;

import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.npc.action.Action;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.item.Item;

public class SellAction extends SelectTextAction {
    private boolean transactionSuccess;
    private Action nextAction;
    private Player player;

    public SellAction(String text, Player player, Action nextAction) {
        super(text);
        this.nextAction = nextAction;
        this.player = player;
        transactionSuccess = false;
    }

    @Override
    public String[] getOptions(NPC target) {
        String options[] = new String[target.getStuff().getInventaire().size()];
        for (int i = 0; i < target.getStuff().getInventaire().size(); i++) {
            options[i] = target.getStuff().getInventaire().get(i).getNom();
        }
        return options;
    }

    @Override
    public Action getNextAction() {
        if (transactionSuccess) {
            return new SpeakAction("Tu n'a pas assez d'argent pour acheter cet objet.", this);
        }
        return nextAction;
    }

    @Override
    public void execute(NPC target) {
        Item selectedItem = target.getStuff().getInventaire().get(getSelection());

        if (selectedItem.getPrix() < player.getStuff().getMoney()) {
            transactionSuccess = true;
        }
        else {
            player.getStuff().getInventaire().add(selectedItem);
            target.getStuff().getInventaire().remove(selectedItem);
            target.getStuff().addMoney(selectedItem.getPrix());
        }
    }
}
