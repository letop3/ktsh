package com.letop3.ktsh.view.entity;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.view.animation.AnimationAdapter;
import com.letop3.ktsh.view.animation.AnimationHandler;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Rectangle2D;
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
		entity.setHitboxSize(spriteTarget.getWidth(), spriteTarget.getHeight());

		spriteTarget.setTranslateX(entity.getPosition().getX() + screenPosition.getX() - (Chunk.CHUNK_SIZE / 22));
		spriteTarget.setTranslateY(entity.getPosition().getY() + screenPosition.getY() - (Chunk.CHUNK_SIZE / 22));

		ChangeListener<Number> xListener = (obs, old, nouv) -> {
			spriteTarget.setTranslateX(entity.getPosition().getX() + screenPosition.getX() - (Chunk.CHUNK_SIZE / 22));
		};
		ChangeListener<Number> yListener = (obs, old, nouv) -> {
			spriteTarget.setTranslateY(entity.getPosition().getY() + screenPosition.getY() - (Chunk.CHUNK_SIZE / 22));
		};

        screenPosition.xProperty().addListener(xListener);
		entity.getPosition().xProperty().addListener(xListener);
        screenPosition.yProperty().addListener(yListener);
		entity.getPosition().yProperty().addListener(yListener);
    }

	public Entity getEntity() {
		return entity;
	}

    public void update() {
        sprite.setImage(animHandler.getFrame());
    }
}
