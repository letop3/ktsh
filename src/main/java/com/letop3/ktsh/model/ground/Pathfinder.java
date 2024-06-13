package com.letop3.ktsh.model.ground;

import java.util.*;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Position;

public class Pathfinder {
    private Ground ground;

    private Position target;
    private int targetX, targetY;

    List<int[]> path;

    public Pathfinder(Position target, Position position, Ground ground) {
        this.ground = ground;

        this.target = target;
        targetX = ground.tileFromPosX(target.getX());
        targetY = ground.tileFromPosY(target.getY());

        this.path = findPath(ground.tileFromPosX(position.getX()), ground.tileFromPosY(position.getY()));
    }

    public void setTarget(Position target, Position position) {
        this.target = target;
        targetX = ground.tileFromPosX(target.getX());
        targetY = ground.tileFromPosY(target.getY());

        path = findPath(ground.tileFromPosX(position.getX()), ground.tileFromPosY(position.getY()));
    }

    private double heuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private List<int[]> getNeighbors(int x, int y) {
        List<int[]> neighbors = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            if (direction != null) {
                int newX = x + direction.getX();
                int newY = y - direction.getY();

                boolean canMove = ground.isTileWalkable(ground.posXFromTile(newX), ground.posYFromTile(newY));

                if (canMove && direction.isDiagonal()) {
                    canMove = ground.isTileWalkable(ground.posXFromTile(x), ground.posYFromTile(newY)) && ground.isTileWalkable(ground.posXFromTile(newX), ground.posYFromTile(y));
                }

                if (canMove) {
                    neighbors.add(new int[] { newX, newY });
                }
            }
        }

        return neighbors;
    }

    public List<int[]> findPath(int startX, int startY) {
        PriorityQueue<int[]> openList = new PriorityQueue<>(Comparator.comparingDouble(n -> n[2]));
        Map<String, double[]> costMap = new HashMap<>();
        Map<String, int[]> parentMap = new HashMap<>();

        double[] startCost = {0, heuristic(startX, startY, targetX, targetY)};
        costMap.put(startX + "," + startY, startCost);
        openList.add(new int[] {startX, startY, (int) (startCost[0] + startCost[1])});

        while (!openList.isEmpty()) {
            int[] current = openList.poll();
            int x = current[0];
            int y = current[1];

            if (x == targetX && y == targetY) {
                return reconstructPath(parentMap, targetX, targetY);
            }

            for (int[] neighbor : getNeighbors(x, y)) {
                int nx = neighbor[0];
                int ny = neighbor[1];
                double newCost = costMap.get(x + "," + y)[0] + 1;

                String neighborKey = nx + "," + ny;
                if (!costMap.containsKey(neighborKey) || newCost < costMap.get(neighborKey)[0]) {
                    costMap.put(neighborKey, new double[] {newCost, heuristic(nx, ny, targetX, targetY)});
                    parentMap.put(neighborKey, new int[] {x, y});
                    openList.add(new int[] {nx, ny, (int) (newCost + heuristic(nx, ny, targetX, targetY))});
                }
            }
        }

        return Collections.emptyList();
    }

    private List<int[]> reconstructPath(Map<String, int[]> parentMap, int endX, int endY) {
        List<int[]> path = new ArrayList<>();
        String currentKey = endX + "," + endY;

        while (parentMap.containsKey(currentKey)) {
            String[] coords = currentKey.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            path.add(new int[] { x, y });
            int[] parent = parentMap.get(currentKey);
            currentKey = parent[0] + "," + parent[1];
        }

        path.add(new int[] {endX, endY});
        Collections.reverse(path);
        return path;
    }

    public Direction getDirection(Position position) {
        int currentX = ground.tileFromPosX(position.getX());
        int currentY = ground.tileFromPosY(position.getY());

        Direction direction = null;
        if (currentX == targetX && currentY == targetY) {
            double deltaX = Math.abs(target.getX() - position.getX());
            double deltaY = Math.abs(target.getY() - position.getY());

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
            int[] nextTile = path.get(0);
            if (ground.posXFromTile(nextTile[0]) - position.getX() <= 5 && ground.posYFromTile(nextTile[1]) - position.getY() <= 5) {
                path.remove(0);
                nextTile = path.get(0);
            }
            direction = Direction.resolvDirection(nextTile[0] - currentX, nextTile[1] - currentY);
        }

        return direction;
    }

    public boolean isArrived() {
        return path.isEmpty();
    }
}
