package com.letop3.ktsh.model.entity.ennemies.ennemieActions;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.ennemies.Enemy;
import com.letop3.ktsh.model.ground.Pathfinder;

public class FleeAction extends EnemyAction {
    private Pathfinder fleePathfinder;

    public FleeAction(Enemy enemy) {
        super(enemy);

        fleePathfinder = new Pathfinder(new Position(200, 100), enemy.getPosition(), enemy.getGround());
    }

    @Override
    public void execute() {
        getTarget().setDirection(fleePathfinder.getDirection(getTarget().getPosition()));
        if (fleePathfinder.isArrived()) {
            getTarget().setAction(null);
        }
    }
}
