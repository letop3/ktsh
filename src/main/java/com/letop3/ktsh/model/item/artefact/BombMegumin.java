package com.letop3.ktsh.model.item.artefact;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.player.Player;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class BombMegumin extends Artefact{

    private Bombe bombe;

    public BombMegumin(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
        this.setIcon("/com/letop3/ktsh/images/item/megumin.png");
    }

    @Override
    public void action(Player player) {
        if (bombe == null){
            double x = player.getPosition().getX() - 16;
            double y = player.getPosition().getY() - 16;
            bombe = new Bombe(new Position(x,y));
            player.getEnv().addBombe(bombe);
            notifyActionListener();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                                player.getEnv().removeBombe(bombe);
                                bombe = null;
                            }
                    );
                }
            }, 3500);
        }
    }
}
