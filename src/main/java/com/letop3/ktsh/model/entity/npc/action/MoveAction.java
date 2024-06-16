package com.letop3.ktsh.model.entity.npc.action;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.ground.Pathfinder;

public class MoveAction implements Action {
    private Action nextAction;
    private Position position;

    public MoveAction(Position position, Action nextAction) {
        this.nextAction = nextAction;
        this.position = position;
    }

    @Override
    public Action getNextAction() {
        return nextAction;
    }

    @Override
    public void execute(NPC target) {
        target.setPathfinder(new Pathfinder(position, target.getPosition(), target.getGround()));
    }
}
