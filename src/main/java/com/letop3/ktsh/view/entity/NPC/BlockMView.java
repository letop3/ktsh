package com.letop3.ktsh.view.entity.NPC;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.view.entity.EntityView;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class BlockMView extends EntityView {
    public BlockMView(Entity entity, Pane spriteTarget, Position screenPosition) {
        super(entity, spriteTarget, screenPosition);
        getSprite().setImage(new Image(getClass().getResourceAsStream("/com/letop3/ktsh/images/entities/blockM.png")));
    }

    @Override
    public void update() {
        
    }
}
