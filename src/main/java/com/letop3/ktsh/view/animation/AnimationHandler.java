package com.letop3.ktsh.view.animation;

import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;


public class AnimationHandler {
    private AnimationAdapter adapter;
    private int currentFrame;
    private int currentImageIndex;
    private Animation currentAnimation;
    private boolean moved;

    public AnimationHandler(AnimationAdapter adapter) {
        this.adapter = adapter;
        currentFrame = 0;
        currentImageIndex = 0;
        currentAnimation = null;
        moved = false;

        ChangeListener move = (obs, old, nouv) -> {
            moved = true;
        };

        adapter.getEntity().getPosition().xProperty().addListener(move);
        adapter.getEntity().getPosition().yProperty().addListener(move);
    }

    public Image getFrame() {
        Animation anim;
        if (moved) {
            anim = adapter.getMovingAnim(adapter.getEntity().getDirection());
        }
        else {
            anim = adapter.getIdleAnim(adapter.getEntity().getDirection());
        }

        if (anim != currentAnimation) {
            currentAnimation = anim;
            currentImageIndex = 0;
            currentFrame = 0;
        }

        if (currentFrame >= anim.getFrameTime()) {
            currentImageIndex = (currentImageIndex + 1) % anim.getLength(); // Faire défiler les images
            currentFrame = 0;
        }
        moved = false;

        currentFrame++;
        return anim.getFrame(currentImageIndex);
    }
}
