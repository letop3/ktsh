package com.letop3.ktsh.view.entity;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.view.animation.AnimationAdapter;
import com.letop3.ktsh.view.animation.AnimationHandler;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class EntityView {
    private final Entity entity;
    private final AnimationHandler animHandler;
    private final ImageView sprite;

    public EntityView(AnimationAdapter animationAdapter, Pane spriteTarget, Position screenPosition) {
        this.animHandler = new AnimationHandler(animationAdapter);
        this.sprite = new ImageView();
		spriteTarget.getChildren().add(sprite);

        entity = animationAdapter.getEntity();

		spriteTarget.setTranslateX(entity.getPosition().getX() + screenPosition.getX());
		spriteTarget.setTranslateY(entity.getPosition().getY() + screenPosition.getY());

        screenPosition.xProperty().addListener((obs, old, nouv) -> {
            spriteTarget.setTranslateX(entity.getPosition().getX() + (double)nouv);
        });
        screenPosition.yProperty().addListener((obs, old, nouv) -> {
            spriteTarget.setTranslateY(entity.getPosition().getY() + (double)nouv);
        });
    }

    public void update() {
        sprite.setImage(animHandler.getFrame());
    }
}
