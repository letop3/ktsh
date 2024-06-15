package com.letop3.ktsh.model.item.consomable;

import com.letop3.ktsh.model.entity.player.Player;

import java.util.Timer;
import java.util.TimerTask;

public class PotionAtk extends Consomable {
    public PotionAtk(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
        this.setIcon("/com/letop3/ktsh/images/item/potionatk.png");
        this.setCooldown(15);
        this.setOnCD(false);
    }

    @Override
    public void action(Player player) {
        if (isOnCD()) {
            return;
        }
        notifyActionListener();
        int baseAtk = player.getAtk();
        player.setAtk(baseAtk + 2);

        setOnCD(true);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                player.setAtk(baseAtk);
                setOnCD(false);
                System.out.println("fin buff atk");
            }
        }, getCooldown() * 1000);
    }
}
