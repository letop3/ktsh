package com.letop3.ktsh.view;

import com.letop3.ktsh.utils.TilesetCutter;

public class GroundView {
    TilesetCutter cutter;

    public GroundView() {
        cutter = new TilesetCutter("src/main/resources/images/ground.png", 32);
    }
}
