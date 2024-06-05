package com.letop3.ktsh.model.ground;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Position;

public class Pathfinder {
    private final Ground ground;
    private Position target;
    private int radius;

    private Map<String, Integer> distancesMap;
    private double currentDistance;

    public Pathfinder(Position target, Ground ground, int radius) {
        this.target = target;
        this.ground = ground;
        this.radius = radius + 100;
        distancesMap = new HashMap<>();
        currentDistance = Double.MAX_VALUE;

        calculateDistance();
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
        calculateDistance();
    }

    private void initializeDistances() {
        for (int y = -radius; y < radius; y++) {
            for (int x = -radius; x < radius; x++) {
                int tileX = ground.tileFromPosX(target.getX()) - x;
                int tileY = ground.tileFromPosY(target.getY()) - y;

                if (x != 0 || y != 0) {
                    distancesMap.put(tileX + "," + tileY, Integer.MAX_VALUE);
                }
                else {
                    distancesMap.put(tileX + "," + tileY, 0);
                }
            }
        }
    }

    private boolean inRadius(int x, int y) {
        return Math.abs(ground.tileFromPosX(target.getX() - x)) <= radius && Math.abs(ground.tileFromPosY(target.getY() - y)) <= radius;
    }

    private void calculateDistance() {
        initializeDistances();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {ground.tileFromPosX(target.getX()), ground.tileFromPosY(target.getY())});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentDistance = distancesMap.get(current[0] + "," + current[1]);

            for (Direction direction : Direction.values()) {
                int newX = current[0] + direction.getX();
                int newY = current[1] + direction.getY();

                if (inRadius(newX, newY) && ground.canMoveTo(ground.posXFromTile(newX), ground.posYFromTile(newY)) && distancesMap.get(newX + "," + newY) == Integer.MAX_VALUE) {
                    if (distancesMap.containsKey(newX + "," + newY)) {
                        distancesMap.put(newX + "," + newY, currentDistance + 1);
                        queue.add(new int[] {newX, newY});
                    }
                }
            }
        }
    }

    public Direction directionToTarget(Position start) {
        int minDistance = Integer.MAX_VALUE;
        Direction direction = null;

        for (Direction dir : Direction.values()) {
            int newX = ground.tileFromPosX(start.getX()) + dir.getX();
            int newY = ground.tileFromPosY(start.getY()) - dir.getY();

            int distance = distancesMap.get(newX + "," + newY);
            if (inRadius(newX, newY) && distance < minDistance && currentDistance >= start.distance(target)) {
                minDistance = distance;
                direction = dir;
                currentDistance = start.distance(target);
            }
        }

        return direction;
    }
}
