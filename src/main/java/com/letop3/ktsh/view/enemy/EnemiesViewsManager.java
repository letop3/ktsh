package com.letop3.ktsh.view.enemy;

import com.letop3.ktsh.model.entity.enemy.Enemy;
import com.letop3.ktsh.model.ground.Ground;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class EnemiesViewsManager {
    private final Ground ground;
    private final Pane gamePlayer;
    ArrayList<EnemyView> enemiesViews;

    public EnemiesViewsManager(ArrayList<Enemy> ennemies, Pane gamePlayer, Ground ground) {
        this.ground = ground;
        this.gamePlayer = gamePlayer;
        this.enemiesViews = new ArrayList<>();
        createEnemiesViews(ennemies);
    }

    private void createEnemiesViews(ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            enemiesViews.add(new EnemyView(enemy, gamePlayer, ground));
        }

    }

    public void update() {
        for (EnemyView enemyView : enemiesViews) {
            enemyView.update();
        }
    }
}
