package com.letop3.ktsh.view.entity;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.view.animation.AnimationAdapter;
import com.letop3.ktsh.view.animation.AnimationHandler;

import javafx.scene.layout.Pane;

public class HumanView extends EntityView {
    private final AnimationHandler animHandler;

    public HumanView(AnimationAdapter animationAdapter, Pane spriteTarget, Position screenPosition) {
        super(animationAdapter.getEntity(), spriteTarget, screenPosition);
        
        this.animHandler = new AnimationHandler(animationAdapter);
    }

    @Override
    public void update() {
        getSprite().setImage(animHandler.getFrame());
    }
}
