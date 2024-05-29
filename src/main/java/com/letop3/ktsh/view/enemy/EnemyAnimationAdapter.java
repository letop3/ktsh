package com.letop3.ktsh.view.enemy;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.enemy.Enemy;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.view.animation.Animation;
import com.letop3.ktsh.view.animation.AnimationAdapter;
import com.letop3.ktsh.view.viewUtils.TilesetCutter;
import javafx.scene.image.Image;

public class EnemyAnimationAdapter implements AnimationAdapter {
    private Enemy enemy;

    Animation idle;
    Animation nAnim, sAnim, eAnim, wAnim;



    public EnemyAnimationAdapter(Enemy enemy) {
        this.enemy = enemy;

        loadAnim();
    }

    private void loadAnim() {
        TilesetCutter cutter = new TilesetCutter(EnemyTilesetMapper.getTilesetPath(enemy), 32);

        Image[] sImages = {cutter.getTile(0), cutter.getTile(1), cutter.getTile(2)};
        Image[] wImages = {cutter.getTile(3), cutter.getTile(4), cutter.getTile(5)};
        Image[] eImages = {cutter.getTile(6), cutter.getTile(7), cutter.getTile(8)};
        Image[] nImages = {cutter.getTile(9), cutter.getTile(10), cutter.getTile(11)};

        idle = new Animation(new Image[] {cutter.getTile(0)});

        nAnim = new Animation(nImages);
        sAnim = new Animation(sImages);
        eAnim = new Animation(eImages);
        wAnim = new Animation(wImages);
    }

    @Override
    public Animation getIdleAnim(Direction direction) {
        return idle;
    }

    @Override
    public Animation getMovingAnim(Direction direction) {
        return switch (direction) {
            case NORTH -> nAnim;
            case SOUTH -> sAnim;
            case EAST -> eAnim;
            case WEST -> wAnim;
            default -> sAnim;
        };
    }

    @Override
    public Entity getEntity() {
        return enemy;
    }
}
