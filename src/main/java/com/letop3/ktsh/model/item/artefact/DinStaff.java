package com.letop3.ktsh.model.item.artefact;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.player.Player;

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
            double x = player.getPosition().getX();
            double y = player.getPosition().getY();
            projectile = new Projectile(new Position(x,y), player.getGround(), player.getLastDirection());
            player.getEnv().addProjo(projectile);
            notifyActionListener();
        }
    }
}
