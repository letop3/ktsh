package com.letop3.ktsh.model.ground;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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

    private int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private void calculateDistance() {
        initializeDistances();

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        int targetX = ground.tileFromPosX(target.getX());
        int targetY = ground.tileFromPosY(target.getY());
        queue.add(new int[] {targetX, targetY, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];
            int currentDistance = distancesMap.get(curX + "," + curY);

            for (Direction direction : Direction.values()) {
                int newX = curX + direction.getX();
                int newY = curY + direction.getY();

                if (inRadius(newX, newY) && ground.canMoveTo(ground.posXFromTile(newX), ground.posYFromTile(newY))) {
                    int newDistance = currentDistance + 1;
                    String newKey = newX + "," + newY;

                    if (newDistance < distancesMap.getOrDefault(newKey, Integer.MAX_VALUE)) {
                        int heuristic = manhattanDistance(newX, newY, targetX, targetY);
                        distancesMap.put(newKey, newDistance);
                        queue.add(new int[] {newX, newY, newDistance + heuristic});
                    }
                }
            }
        }
    }

    public Direction directionToTarget(Position start) {
        if (currentDistance < start.distance(target)) return null;

        int startX = ground.tileFromPosX(start.getX());
        int startY = ground.tileFromPosY(start.getY());
        int targetX = ground.tileFromPosX(target.getX());
        int targetY = ground.tileFromPosY(target.getY());

        Direction direction = null;

        if (startX == targetX && startY == targetY) {
            double deltaX = target.getX() - start.getX();
            double deltaY = target.getY() - start.getY();

            if (Math.abs(deltaX) > 2) {
                direction = deltaX > 0 ? Direction.EAST : Direction.WEST;
            }
            if (Math.abs(deltaY) > 2) {
                Direction dirY = deltaY < 0 ? Direction.NORTH : Direction.SOUTH;

                if (direction == null) direction = dirY;
                else direction = direction.add(dirY);
            }

            currentDistance = start.distance(target);
        }
        else {
            int minDistance = Integer.MAX_VALUE;

            for (Direction dir : Direction.values()) {
                int newX = startX + dir.getX();
                int newY = startY - dir.getY();

                int distance = distancesMap.getOrDefault(newX + "," + newY, Integer.MAX_VALUE);

                if (inRadius(newX, newY) && distance < minDistance) {
                    minDistance = distance;
                    direction = dir;
                    currentDistance = start.distance(target);
                }
            }
        }

        return direction;
    }
}
