package com.letop3.ktsh.model.entity.ennemies.ennemieActions;

import com.letop3.ktsh.model.entity.ennemies.Enemy;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Pathfinder;

import javafx.beans.value.ChangeListener;

public class AttackAction extends EnemyAction {
    private Pathfinder playerfinder;

    public AttackAction(Enemy enemy) {
        super(enemy);

        playerfinder = new Pathfinder(enemy.getPlayer().getPosition(), enemy.getPosition(), enemy.getPlayer().getGround());

        ChangeListener<Number> playerListener = (observable, oldValue, newValue) -> {
            playerfinder.setTarget(enemy.getPlayer().getPosition(), enemy.getPosition());
        };

        enemy.getPlayer().getPosition().xProperty().addListener(playerListener);
        enemy.getPlayer().getPosition().yProperty().addListener(playerListener);
    }

    @Override
    public void execute() {
        getTarget().setDirection(playerfinder.getDirection(getTarget().getPosition()));
        if (playerfinder.isArrived() || getTarget().getPlayer().getPosition().distance(getTarget().getPosition()) <= (Chunk.CHUNK_SIZE / 11) + 10) getTarget().attack();
    }
}
