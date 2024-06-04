package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.model.item.Item;
import com.letop3.ktsh.model.item.arme.Shield;
import com.letop3.ktsh.model.item.arme.Sword;
import com.letop3.ktsh.model.item.artefact.Artefact;
import com.letop3.ktsh.model.item.consomable.Consomable;
import com.letop3.ktsh.view.GameView;
import com.letop3.ktsh.view.StuffClickListener;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class StuffController implements Initializable, StuffClickListener {
    private GameView view;
    private Env env;

    public StuffController(GameView view, Env env) {
        this.view = view;
        this.env = env;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Passer le listener à la vue
        view.getStuffView().setStuffClickListener(this);

        // ajout listener pour update hb bar
        view.update();
        env.getPlayer().hpProperty().addListener((observable, oldValue, newValue) -> {
            view.update();
        });

        // Test perte hp pour update bar hp
        Timer timer = new Timer();
        for (int i = 1; i <= 10; i++) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("-1 hp");
                    env.getPlayer().setHp(env.getPlayer().getHp() - 1);
                }
            }, i * 5000);
        }

        // ajouter listener sur changement stuff
        env.getPlayer().getStuff().getInventaire().addListener(new ListChangeListener<Item>() {
            @Override
            public void onChanged(Change<? extends Item> change) {
                Platform.runLater(() -> view.getStuffView().updateStuff(env.getPlayer().getStuff())); //Platform.runLater fait tourner sur le meme thread de l'app javafx
            }
        });

        view.getStuffView().updateStuff(env.getPlayer().getStuff());

        //bind entre lock de Player et isVisible de StuffView
        env.getPlayer().lockProperty().bind(view.getStuffView().isVisibleProperty());
    }

    @Override
    public void onMainGClick() {
        System.out.println("Main Gauche cliqué dans GameController");
        Item itemSelected = env.getPlayer().getStuff().getMainG();
        if (itemSelected != null) {
            env.getPlayer().getStuff().addItem(itemSelected);
            env.getPlayer().getStuff().setMainG();
        }
    }

    @Override
    public void onMainDClick() {
        System.out.println("Main Droite cliqué dans GameController");
        Item itemSelected = env.getPlayer().getStuff().getMainD();
        if (itemSelected != null) {
            env.getPlayer().getStuff().addItem(itemSelected);
            env.getPlayer().getStuff().setMainD();
        }
    }

    @Override
    public void onQuickSlotClick() {
        System.out.println("QuickSlot cliqué dans GameController");
        Item itemSelected = env.getPlayer().getStuff().getQuickSlot();
        if (itemSelected != null) {
            env.getPlayer().getStuff().addItem(itemSelected);
            env.getPlayer().getStuff().setQuickSlot();
        }
    }

    @Override
    public void onStuffClick(int i) {
        System.out.println("Stuff cliqué dans GameController, id : " + i);
        Item itemSelected = env.getPlayer().getStuff().getInventaire().get(i);
        if (itemSelected != null) {
            Item currentItem = null;
            if (itemSelected instanceof Sword) {
                currentItem = env.getPlayer().getStuff().getMainG();
                env.getPlayer().getStuff().setMainG(itemSelected);
            } else if (itemSelected instanceof Shield) {
                currentItem = env.getPlayer().getStuff().getMainD();
                env.getPlayer().getStuff().setMainD(itemSelected);
            } else if (itemSelected instanceof Consomable || itemSelected instanceof Artefact) {
                currentItem = env.getPlayer().getStuff().getQuickSlot();
                env.getPlayer().getStuff().setQuickSlot(itemSelected);
            }
            if (currentItem != null) {
                env.getPlayer().getStuff().addItem(currentItem);
            }
            env.getPlayer().getStuff().removeItem(itemSelected);
        }
    }
}
