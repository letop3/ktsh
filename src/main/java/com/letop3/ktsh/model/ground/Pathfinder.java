package com.letop3.ktsh.model.ground;

import java.util.*;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Position;

public class Pathfinder {
    private Ground ground;
    private int targetX, targetY;
    private List<double[]> path;

    public Pathfinder(Position target, Position position, Ground ground) {
        this.ground = ground;
        setTarget(target, position);
    }

    public void setTarget(Position target, Position position) {
        this.targetX = ground.tileFromPosX(target.getX());
        this.targetY = ground.tileFromPosY(target.getY());
        this.path = findPath(ground.tileFromPosX(position.getX()), ground.tileFromPosY(position.getY()));
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
                boolean canMove = ground.isTileWalkable(ground.posXFromTile(newX), ground.posYFromTile(newY), direction);

                if (canMove && direction.isDiagonal()) {
                    canMove = ground.isTileWalkable(ground.posXFromTile(x), ground.posYFromTile(newY), direction)
                            && ground.isTileWalkable(ground.posXFromTile(newX), ground.posYFromTile(y), direction);
                }

                if (canMove) neighbors.add(new int[] { newX, newY });
            }
        }

        return neighbors;
    }

    private List<double[]> findPath(int startX, int startY) {
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

        System.out.println("aucun chemin trouvé");
        return Collections.emptyList();
    }

    private List<double[]> reconstructPath(Map<String, int[]> parentMap, int endX, int endY) {
        List<double[]> path = new ArrayList<>();
        String currentKey = targetX + "," + targetY;

        while (parentMap.containsKey(currentKey)) {
            String[] coords = currentKey.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            path.add(new double[] {ground.posXFromTile(x), ground.posYFromTile(y)});
            int[] parent = parentMap.get(currentKey);
            currentKey = parent[0] + "," + parent[1];
        }

        path.add(new double[] {ground.posXFromTile(endX), ground.posYFromTile(endY)});
        Collections.reverse(path);
        path.remove(0);

        return path;
    }

    public Direction getDirection(Position position) {
        if (path.isEmpty()) return null;

        double[] nextPos = path.get(0);
        double deltaX = nextPos[0] - position.getX();
        double deltaY = nextPos[1] - position.getY();

        Direction direction = null;

        if (Math.abs(deltaX) > 2) {
            direction = deltaX > 0 ? Direction.EAST : Direction.WEST;
        }

        if (Math.abs(deltaY) > 2) {
            Direction dirY = deltaY > 0 ? Direction.SOUTH : Direction.NORTH;

            if (direction == null) {
                direction = dirY;
            } else {
                direction = direction.add(dirY);
            }
        }

        if (direction == null) {
            path.remove(0);
            if (!path.isEmpty()) direction = getDirection(position);
        }

        return direction;
    }

    public boolean isArrived() {
        return path.isEmpty();
    }
}
