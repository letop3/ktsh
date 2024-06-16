package com.letop3.ktsh.model.entity.ennemies.ennemieActions;

import com.letop3.ktsh.model.entity.ennemies.Enemy;

public abstract class EnemyAction {
    protected Enemy target;

    public EnemyAction(Enemy enemy) {
        target = enemy;
    }

    public Enemy getTarget() {
        return target;
    }

    public abstract void execute();
}
