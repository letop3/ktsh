package com.letop3.ktsh.view.animation;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Entity;

public interface AnimationAdapter {
    Animation getIdleAnim(Direction direction);
    Animation getMovingAnim(Direction direction);

    Entity getEntity();
}
