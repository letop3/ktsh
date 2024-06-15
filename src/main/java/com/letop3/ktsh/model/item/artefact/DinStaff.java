package com.letop3.ktsh.model.item.artefact;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.player.Player;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class DinStaff extends Artefact{

    private Projectile projectile = null;

    public DinStaff(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
        this.setIcon("/com/letop3/ktsh/images/item/dinstaff.png");
    }

    public Projectile getProjectile() {
        return projectile;
    }

    @Override
    public void action(Player player) {
        if (projectile == null){
            player.setEnAtq(true);
            double x = player.getPosition().getX() - 16;
            double y = player.getPosition().getY() - 16;
            projectile = new Projectile(new Position(x,y), player.getGround(), player.getLastDirection());
            player.getEnv().addProjo(projectile);
            notifyActionListener();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                                player.getEnv().removeProjo(projectile);
                                projectile = null;
                                player.setEnAtq(false);
                            }
                    );
                }
            }, 750);
        }
    }
}
