package com.letop3.ktsh.model.item.artefact;

import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;

import java.util.Timer;
import java.util.TimerTask;

public class BotteErmS extends Artefact {
    public BotteErmS(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
        this.setIcon("/com/letop3/ktsh/images/item/erm's.png");
        setOnCD(false);
        setCooldown(3);
    }

    @Override
    public void action(Player player) {
        if (player.getDirection() != null && !isOnCD()) {
            notifyActionListener();

            double newX = player.getPosition().getX() + player.getDirection().getX() * (Chunk.CHUNK_SIZE/11);
            double newY = player.getPosition().getY() - player.getDirection().getY() * (Chunk.CHUNK_SIZE/11);

            int tpCount = 0;
            while (player.getGround().isTileWalkable(newX + player.getDirection().getX() * (Chunk.CHUNK_SIZE/11), newY - player.getDirection().getY() * (Chunk.CHUNK_SIZE/11)) && tpCount < 2){
                newX += player.getDirection().getX() * (Chunk.CHUNK_SIZE/11);
                newY -= player.getDirection().getY() * (Chunk.CHUNK_SIZE/11);
                tpCount++;
            }

            player.getPosition().setX(newX);
            player.getPosition().setY(newY);

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
