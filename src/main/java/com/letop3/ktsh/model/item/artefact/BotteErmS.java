package com.letop3.ktsh.model.item.artefact;

import com.letop3.ktsh.model.entity.player.Player;

import java.util.Timer;
import java.util.TimerTask;

public class BotteErmS extends Artefact{
    public BotteErmS(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
        this.setIcon("/com/letop3/ktsh/images/item/erm's.png");
        setOnCD(false);
        setCooldown(3);
    }

    @Override
    public void action(Player player) {
        if (player.getDirection() != null && !isOnCD()) {
            player.getPosition().setX(player.getPosition().getX() + player.getDirection().getX() * 64);
            player.getPosition().setY(player.getPosition().getY() - player.getDirection().getY() * 64);

            this.setOnCD(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    setOnCD(false);
                }
            }, getCooldown() * 1000);
        }
    }
}
