package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.model.item.artefact.BombMegumin;
import com.letop3.ktsh.model.item.artefact.BotteErmS;
import com.letop3.ktsh.model.item.artefact.DinStaff;
import com.letop3.ktsh.model.item.consomable.PotionAtk;
import com.letop3.ktsh.view.GameView;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable, ActionListener {
    private GameView view;
    private Env env;

    public ItemController(GameView view, Env env) {
        this.view = view;
        this.env = env;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void onActionCalled() {
        if (env.getPlayer().getStuff().getQuickSlot() instanceof BotteErmS) {
            view.getItemView().drawErmS();
        } else if (env.getPlayer().getStuff().getQuickSlot() instanceof PotionAtk) {
            view.getItemView().drawPotionAtk();
        } else if (env.getPlayer().getStuff().getQuickSlot() instanceof DinStaff){
            view.getItemView().drawDinStaff(env);
        } else if (env.getPlayer().getStuff().getQuickSlot() instanceof BombMegumin)
            view.getItemView().drawBombe(env);
    }
}
