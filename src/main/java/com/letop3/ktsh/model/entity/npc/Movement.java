package com.letop3.ktsh.model.entity.npc;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Position;

import java.util.ArrayList;

public class Movement {
    ArrayList<Position> positions;
    Position actualPosition;
    Direction direction;

    public Movement(ArrayList<Position> p) throws IllegalArgumentException{
        if(p.size() == 0) {
            throw new IllegalArgumentException("Movement must have at least one position");
        }
        this.positions = p;
        this.actualPosition = p.get(0);
    }

    public Position getActualPosition() {
        return this.actualPosition;
    }

    public Position calculateNextPosition() {
        return null;
    }

    public ArrayList<Position> getPositions() {
        return this.positions;
    }



}
