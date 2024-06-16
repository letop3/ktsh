package com.letop3.ktsh.view.entity;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.ground.Chunk;

import javafx.beans.value.ChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class EntityView {
    private final Entity entity;
    private final ImageView sprite;
    private Pane spriteTarget;

    public EntityView(Entity entity, Pane spriteTarget, Position screenPosition) {
        this.sprite = new ImageView();
        spriteTarget.getChildren().add(sprite);
        this.spriteTarget = spriteTarget;

        this.entity = entity;
        this.entity.setHitboxSize(spriteTarget.getWidth(), spriteTarget.getHeight());

        spriteTarget.setTranslateX(this.entity.getPosition().getX() + screenPosition.getX() - (Chunk.CHUNK_SIZE / 22));
        spriteTarget.setTranslateY(this.entity.getPosition().getY() + screenPosition.getY() - (Chunk.CHUNK_SIZE / 22));

        ChangeListener<Number> xListener = (obs, old, nouv) -> {
            spriteTarget.setTranslateX(this.entity.getPosition().getX() + screenPosition.getX() - (Chunk.CHUNK_SIZE / 22));
        };
        ChangeListener<Number> yListener = (obs, old, nouv) -> {
            spriteTarget.setTranslateY(this.entity.getPosition().getY() + screenPosition.getY() - (Chunk.CHUNK_SIZE / 22));
        };

        screenPosition.xProperty().addListener(xListener);
        this.entity.getPosition().xProperty().addListener(xListener);
        screenPosition.yProperty().addListener(yListener);
        this.entity.getPosition().yProperty().addListener(yListener);

//        ImageView testNpcHB = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/letop3/ktsh/images/player/hitbox.png"))));
//        testNpcHB.setFitHeight(spriteTarget.getHeight());
//        testNpcHB.setFitWidth(spriteTarget.getWidth());
//        spriteTarget.getChildren().add(testNpcHB);

    }

    public Pane getSpriteTarget() {
        return spriteTarget;
    }

    public Entity getEntity() {
        return entity;
    }

	public ImageView getSprite() {
		return sprite;
	}

    public abstract void update();
}
