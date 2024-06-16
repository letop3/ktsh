package com.letop3.ktsh.model;

public abstract class Updatable {
    private long lastUpdate;

    public Updatable() {
        lastUpdate = 0;
    }

    protected abstract void update(long frame);

    public void doUpdate(long frame) {
        if (frame > lastUpdate) {
            update(frame);

            lastUpdate = frame;
        }
    }
}
