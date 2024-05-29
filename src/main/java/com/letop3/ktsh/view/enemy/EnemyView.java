package com.letop3.ktsh.view.enemy;

import com.letop3.ktsh.model.entity.enemy.Enemy;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.view.animation.AnimationHandler;
import com.letop3.ktsh.view.player.PlayerAnimationAdapter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EnemyView {
    private final Ground ground;
    private final Enemy enemy;
    private final Pane gamePlayer;
    private final ImageView enemyImageView;
    private final AnimationHandler animHandler;

    public EnemyView(Enemy enemy, Pane gamePlayer, Ground ground) {
        this.ground = ground;
        this.enemy = enemy;
        this.gamePlayer = gamePlayer;

        animHandler = new AnimationHandler(new EnemyAnimationAdapter(enemy));

        this.enemyImageView = new ImageView(animHandler.getFrame());

        gamePlayer.getChildren().add(enemyImageView);

        enemyImageView.layoutXProperty().bind(enemy.getPosition().xProperty());
        enemyImageView.layoutYProperty().bind(enemy.getPosition().yProperty());
    }

    public void update() {
        enemy.update(ground);
        enemyImageView.setImage(animHandler.getFrame());
    }
}
