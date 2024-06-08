package com.letop3.ktsh.model.entity.npc;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Interractible;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.npc.action.Action;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.ground.Pathfinder;
import javafx.geometry.Rectangle2D;

public class NPC extends Interractible {
    private NPCInterractListener interractListener;
    private final Action dialogue;

    private Pathfinder pathfinder;

    public NPC(Position position, Ground ground, Action dialogue) {
        super(position, ground);
        this.dialogue = dialogue;
        interractListener = null;
        pathfinder = null;
    }

    public void setPathfinder(Pathfinder pathfinder) {
        this.pathfinder = pathfinder;
    }

    public Action getDialogue() {
        return dialogue;
    }

    public void setInterractListener(NPCInterractListener interractListener) {
        this.interractListener = interractListener;
    }

    @Override
    public boolean isInterractible(Player player) {
        return super.isInterractible(player) && dialogue != null;
    }

    @Override
    public void interract() {
        if (interractListener != null) interractListener.interract();
    }

    @Override
    public String toString() {
        return "Npc";
    }

    @Override
    public void update() {
        super.update();
        if (pathfinder != null) {
            Direction direction = pathfinder.directionToTarget(getPosition());
            setDirection(direction);
            if (direction == null) pathfinder = null;
        }
    }

    @Override
    public Rectangle2D getHitbox() {
        double x = getPosition().getX();
        double y = getPosition().getY();
        double width = 32;
        double height = 32;

        return new Rectangle2D(x, y, width, height);
    }
}
