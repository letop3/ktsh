package com.letop3.ktsh.model.item.artefact;

import com.letop3.ktsh.model.entity.BlockM;
import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.player.Player;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;

import java.util.Timer;
import java.util.TimerTask;

public class GantHerkUl extends Artefact {
    public GantHerkUl(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
        this.setIcon("/com/letop3/ktsh/images/item/herkul.png");
    }

    @Override
    public void action(Player player) {
        for (Entity e : player.getGround().getCurrentChunk().getEntities()){
            if (!(e instanceof BlockM))
                return;

            Rectangle2D zoneJoueur = new Rectangle2D(player.getPosition().getX(), player.getPosition().getY(),32,32);
            Rectangle2D zoneGauche = new Rectangle2D(e.getPosition().getX()-32, e.getPosition().getY(),32,32);
            Rectangle2D zoneDroit = new Rectangle2D(e.getPosition().getX()+32, e.getPosition().getY(),32,32);
            Rectangle2D zoneHaut = new Rectangle2D(e.getPosition().getX(), e.getPosition().getY()-32,32,32);
            Rectangle2D zoneBas = new Rectangle2D(e.getPosition().getX(), e.getPosition().getY()+32,32,32);

            if (player.getLastDirection().equals(Direction.EAST)
                    && zoneJoueur.intersects(zoneGauche)){
                e.setDirection(Direction.EAST);
            }

            else if (player.getLastDirection().equals(Direction.WEST)
                    && zoneJoueur.intersects(zoneDroit)){
                e.setDirection(Direction.WEST);
            }

            else if (player.getLastDirection().equals(Direction.SOUTH)
                    && zoneJoueur.intersects(zoneHaut)){
                e.setDirection(Direction.SOUTH);
            }

            else if (player.getLastDirection().equals(Direction.NORTH)
                    && zoneJoueur.intersects(zoneBas)){
                e.setDirection(Direction.NORTH);
            }
        }
    }
}
