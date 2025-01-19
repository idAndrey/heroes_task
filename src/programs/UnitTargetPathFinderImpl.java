package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.Edge;
import com.battle.heroes.army.programs.EdgeDistance;
import com.battle.heroes.army.programs.UnitTargetPathFinder;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.io.IOException;

public class UnitTargetPathFinderImpl implements UnitTargetPathFinder {

    @Override
    public List<Edge> getTargetPath(Unit attackUnit, Unit targetUnit, List<Unit> existingUnitList) {

        // Ваше решение

        int width = 27, height = 21;
        boolean[][] obstacles = new boolean[width][height];
        boolean[][] obstaclesTranspon = new boolean[height][width];
        boolean[][] battleField = new boolean[height][width];


        for (Unit unit : existingUnitList) {
            if (unit.isAlive()) {
                obstacles[unit.getxCoordinate()][unit.getyCoordinate()] = true;
                obstaclesTranspon[unit.getyCoordinate()][unit.getxCoordinate()] = true;
                battleField[unit.getyCoordinate()][unit.getxCoordinate()] = true;
            }
        }

        obstacles[targetUnit.getxCoordinate()][targetUnit.getyCoordinate()] = false;

        char[][] battleFieldChar = new char[height][width];

        for ( int i = 0 ; i < battleField.length; i++)
            for ( int j = 0; j < battleField[i].length; j++)
                battleFieldChar[i][j] = battleField[i][j]?'x':' ';

        battleFieldChar[attackUnit.getyCoordinate()][attackUnit.getxCoordinate()] = 'A';
        battleFieldChar[targetUnit.getyCoordinate()][targetUnit.getxCoordinate()] = 'T';

        String battleFieldOut;
        battleFieldOut = Arrays.deepToString(battleFieldChar).replace("], ", "]\n");
        battleFieldOut = battleFieldOut.replace("[[", "[");
        battleFieldOut = battleFieldOut.replace("]]", "]");

        PriorityQueue<EdgeDistance> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.getDistance()));
        pq.add(new EdgeDistance(attackUnit.getxCoordinate(), attackUnit.getyCoordinate(), 0));

        Iterator iterator = pq.iterator();

        String pqOut = new String();
        while (iterator.hasNext()) {
            EdgeDistance elem = (EdgeDistance) iterator.next();
            pqOut = "(" + elem.getX() + ", " + elem.getY() + ") dis = " + elem.getDistance() + "\n";
        }

        Map<String, Edge> cameFrom = new HashMap<>();
        boolean[][] visited = new boolean[width][height];
        boolean[][] visitedTranspon = new boolean[height][width];
        char[][] visitedTransponChar = new char[height][width];

        for ( int i = 0 ; i < visitedTranspon.length; i++)
            for ( int j = 0; j < visitedTranspon[i].length; j++)
                visitedTransponChar[i][j] = visitedTranspon[i][j]?'x':' ';

        while (!pq.isEmpty()) {
            EdgeDistance current = pq.poll();

            boolean checkiIf = visited[current.getX()][current.getY()];

            if (visited[current.getX()][current.getY()]) continue;

            visitedTransponChar[attackUnit.getyCoordinate()][attackUnit.getxCoordinate()] = 'A';
            visitedTransponChar[targetUnit.getyCoordinate()][targetUnit.getxCoordinate()] = 'T';

            for ( int i = 0 ; i < battleFieldChar.length; i++)
                for ( int j = 0; j < battleFieldChar[i].length; j++)
                    if( battleFieldChar[i][j]=='x') visitedTransponChar[i][j] = 'U';

            visited[current.getX()][current.getY()] = true;
            visitedTranspon[current.getY()][current.getX()] = true;
            visitedTransponChar[current.getY()][current.getX()] = 'v';

            boolean checkSTOP = false;
            boolean checkCurX = false;
            boolean checkCurY = false;
            if(current.getX() == targetUnit.getxCoordinate()) checkCurX = true;
            if(current.getY() == targetUnit.getyCoordinate()) checkCurY = true;
            if(checkCurX && checkCurY) checkSTOP = true;

            if (current.getX() == targetUnit.getxCoordinate() && current.getY() == targetUnit.getyCoordinate()) {
                List<Edge> path = new ArrayList<>();
                String key = current.getX() + "," + current.getY();

                while (cameFrom.containsKey(key)) {
                    Edge edge = cameFrom.get(key);
                    path.add(0, edge);
                    key = edge.getX() + "," + edge.getY();
                }

                path.add(new Edge(targetUnit.getxCoordinate(), targetUnit.getyCoordinate()));

                return path;
            }

            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

            for (int[] nextDirection :  directions) {

                int nx = current.getX() + nextDirection[0], ny = current.getY() + nextDirection[1];
                if (nx < 0 || ny < 0 || nx >= width || ny >= height || obstacles[nx][ny]) continue;

                String key = nx + "," + ny;
                if (!visited[nx][ny] && !cameFrom.containsKey(key)) {
                    pq.add(new EdgeDistance(nx, ny, current.getDistance() + 1));
                    cameFrom.put(key, new Edge(current.getX(), current.getY()));
                }
           }

            Iterator newIterator = pq.iterator();

            pqOut = "";
            while (newIterator.hasNext()) {
                EdgeDistance elem = (EdgeDistance) newIterator.next();
                pqOut = pqOut + "(" + elem.getX() + ", " + elem.getY() + ") dis = " + elem.getDistance() + "\n";
            }
        }

        return new ArrayList<>(); // Путь не найден    }
    }
}
