package com.letop3.ktsh.model.utils;

import javafx.geometry.Rectangle2D;

public class EntityBlock extends Rectangle2D {
    private static final double width = 32;
    private static final double height = 32;

    public EntityBlock(double minX, double minY) {
        super(minX, minY, width, height);
    }

    public boolean intersects(EntityBlock other) {
        return this.intersects(other);
    }

}
