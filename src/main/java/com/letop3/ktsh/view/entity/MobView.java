package com.letop3.ktsh.view.entity;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.view.animation.AnimationAdapter;

import javafx.scene.layout.Pane;

public class MobView extends HumanView {
    public MobView(AnimationAdapter animationAdapter, Pane spriteTarget, Position screenPosition) {
        super(animationAdapter, spriteTarget, screenPosition);
    }
    
    @Override
    public void update() {
        super.update();
    }
}
