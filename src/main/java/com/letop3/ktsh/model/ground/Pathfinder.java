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

    private int targetX;
    private int targetY;

    private Map<String, Integer> distancesMap;
    private double currentDistance;

    public Pathfinder(Position target, Ground ground, int radius) {
        this.target = target;
        this.ground = ground;
        this.radius = radius;
        distancesMap = new HashMap<>();
        currentDistance = Double.MAX_VALUE;

        targetX = ground.tileFromPosX(target.getX());
        targetY = ground.tileFromPosY(target.getY());

        calculateDistance();
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
        currentDistance = Double.MAX_VALUE;
        targetX = ground.tileFromPosX(target.getX());
        targetY = ground.tileFromPosY(target.getY());
        calculateDistance();
    }

    private boolean inRadius(int x, int y) {
        return Math.abs(targetX - x) <= radius && Math.abs(targetY - y) <= radius;
    }

    private void calculateDistance() {
        distancesMap.clear();
        distancesMap.put(targetX + "," + targetY, 0);

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        queue.add(new int[] {targetX, targetY, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];
            int currentDistance = distancesMap.get(curX + "," + curY);

            for (Direction direction : Direction.values()) {
                int newX = curX + direction.getX();
                int newY = curY + direction.getY();


                if (inRadius(newX, newY) && ground.isTileWalkable(ground.posXFromTile(newX), ground.posYFromTile(newY)) &&
                    (direction.isDiagonal() && ground.isTileWalkable(ground.posXFromTile(curX), ground.posYFromTile(newY)) && ground.isTileWalkable(ground.posXFromTile(newX), ground.posYFromTile(curY)))) {
                    int newDistance = currentDistance + 1;
                    String newKey = newX + "," + newY;

                    if (newDistance < distancesMap.getOrDefault(newKey, Integer.MAX_VALUE)) {
                        int heuristic = Math.abs(newX - targetX) + Math.abs(newY - targetY); // Manhattan distance
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
        }
        else {
            int minDistance = Integer.MAX_VALUE;

            for (Direction dir : Direction.values()) {
                int newX = startX + dir.getX();
                int newY = startY - dir.getY();

                int distance = distancesMap.getOrDefault(newX + "," + newY, Integer.MAX_VALUE);
                if (inRadius(newX, newY) && distance <= minDistance) {
                    minDistance = distance;
                    direction = dir;
                }
            }
        }
        
        currentDistance = start.distance(target);

        return direction;
    }
}
