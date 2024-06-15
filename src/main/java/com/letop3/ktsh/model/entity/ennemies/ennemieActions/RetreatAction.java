package com.letop3.ktsh.model.entity.ennemies.ennemieActions;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.ennemies.Enemy;
import com.letop3.ktsh.model.ground.Chunk;

public class RetreatAction extends EnemyAction {
    private Direction direction;
    private double retreatDistance;

    public RetreatAction(Enemy enemy) {
        super(enemy);

        int directionX = (int) (getTarget().getPlayer().getPosition().getX() - getTarget().getPosition().getX());
        int directionY = (int) (getTarget().getPlayer().getPosition().getY() - getTarget().getPosition().getY());
        direction = Direction.resolvDirection(directionX, directionY);

        retreatDistance = 3 * (Chunk.CHUNK_SIZE / 11);
    }

    @Override
    public void execute() {
        getTarget().setDirection(direction);
        if (getTarget().getPlayer().getPosition().distance(getTarget().getPosition()) >= retreatDistance) {
            getTarget().setDirection(null);
        }
    }
}
