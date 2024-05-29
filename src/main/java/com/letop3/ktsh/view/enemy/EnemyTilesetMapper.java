package com.letop3.ktsh.view.enemy;

import com.letop3.ktsh.model.entity.enemy.Enemy;
import com.letop3.ktsh.model.entity.enemy.types.Gobelin;

public class EnemyTilesetMapper {
    private static String GOBELIN_PATH = "/com/letop3/ktsh/images/enemy/gobelin/gobelin.png";
    public static String getTilesetPath(Enemy enemy) {
        if (enemy instanceof Gobelin) {
            return GOBELIN_PATH;
        } else {
            return null; // Ou une valeur par défaut
        }
    }
}
