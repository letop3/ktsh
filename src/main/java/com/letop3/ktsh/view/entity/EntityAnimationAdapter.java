package com.letop3.ktsh.view.entity;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.view.animation.Animation;
import com.letop3.ktsh.view.animation.AnimationAdapter;
import com.letop3.ktsh.view.viewUtils.TilesetCutter;

import javafx.scene.image.Image;

public class EntityAnimationAdapter implements AnimationAdapter {
    private Entity entity;

    Animation nIdle, sIdle, eIdle, wIdle;
    Animation nAnim, sAnim, eAnim, wAnim;

    public EntityAnimationAdapter(Entity entity) {
        this.entity = entity;

        loadAnim();
    }

    private void loadAnim() {
        TilesetCutter cutter = new TilesetCutter("/com/letop3/ktsh/images/player/player.png", 32);

        Image[] sImages = {cutter.getTile(0), cutter.getTile(1), cutter.getTile(2)};
        Image[] wImages = {cutter.getTile(3), cutter.getTile(4)};
        Image[] eImages = {cutter.getTile(5), cutter.getTile(6)};
        Image[] nImages = {cutter.getTile(7), cutter.getTile(8), cutter.getTile(9)};

        nIdle = new Animation(new Image[] {cutter.getTile(8)});
        sIdle = new Animation(new Image[] {cutter.getTile(0)});
        eIdle = new Animation(new Image[] {cutter.getTile(6)});
        wIdle = new Animation(new Image[] {cutter.getTile(3)});

        nAnim = new Animation(nImages);
        sAnim = new Animation(sImages);
        eAnim = new Animation(eImages);
        wAnim = new Animation(wImages);
    }

    @java.lang.Override
    public Animation getIdleAnim(Direction direction) {
        return switch (direction) {
            case NORTH -> nIdle;
            case SOUTH -> sIdle;
            case EAST -> eIdle;
            case WEST -> wIdle;
            default -> nIdle;
        };
    }

    @java.lang.Override
    public Animation getMovingAnim(Direction direction) {
        return switch (direction) {
            case NORTH, NORTH_EAST, NORTH_WEST -> nAnim;
            case SOUTH, SOUTH_EAST, SOUTH_WEST -> sAnim;
            case EAST -> eAnim;
            case WEST -> wAnim;
            default -> sAnim;
        };
    }

    @java.lang.Override
    public Entity getEntity() {
        return entity;
    }
}
