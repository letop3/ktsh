package com.letop3.ktsh.view.animation;

import com.letop3.ktsh.view.viewUtils.TilesetCutter;
import javafx.scene.image.Image;

public class Animation {
    private Image[] frames;
    private int frameTime;

    public Animation(Image[] frames) {
        this.frames = frames;
        this.frameTime = 5;
    }

    public Animation(Image[] frames, int fps) {
        this.frames = frames;
        this.frameTime = fps;
    }

    public Image getFrame(int i) {
        return frames[i];
    }

    public int getLength() {
        return frames.length;
    }

    public int getFrameTime() {
        return frameTime;
    }
}
