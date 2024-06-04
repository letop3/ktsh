package com.letop3.ktsh.view.entity;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.view.animation.AnimationAdapter;
import com.letop3.ktsh.view.animation.AnimationHandler;

import javafx.scene.image.ImageView;

public class EntityView {
    private final Entity entity;
    private final AnimationHandler animHandler;
    private final ImageView spriteTarget;

    public EntityView(AnimationAdapter animationAdapter, ImageView spriteTarget, Position screenPosition) {
        this.animHandler = new AnimationHandler(animationAdapter);
        this.spriteTarget = spriteTarget;

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
        spriteTarget.setImage(animHandler.getFrame());
    }
}
