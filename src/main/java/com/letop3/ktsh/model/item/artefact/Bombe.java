package com.letop3.ktsh.model.item.artefact;

import com.letop3.ktsh.model.entity.Position;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Timer;
import java.util.TimerTask;

public class Bombe {

    private Position position;
    private IntegerProperty detonation;

    public Bombe(Position position) {
        this.position = position;
        this.detonation = new SimpleIntegerProperty(0);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> detonation.set(1));
            }
        }, 3000);
    }

    public IntegerProperty detonationProperty() {
        return detonation;
    }

    public Position getPosition() {
        return position;
    }
}
