package com.letop3.ktsh.view.entity.NPC;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.view.animation.AnimationAdapter;
import com.letop3.ktsh.view.entity.EntityView;
import javafx.scene.layout.Pane;

public class BlockMView extends EntityView {
    public BlockMView(AnimationAdapter animationAdapter, Pane spriteTarget, Position screenPosition) {
        super(animationAdapter, spriteTarget, screenPosition);
    }

    @Override
    public void update() {
        super.update();
    }
}
