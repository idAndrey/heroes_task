package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.Edge;
import com.battle.heroes.army.programs.EdgeDistance;
import com.battle.heroes.army.programs.UnitTargetPathFinder;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;
import java.io.IOException;

public class UnitTargetPathFinderImpl implements UnitTargetPathFinder {

    @Override
    public List<Edge> getTargetPath(Unit attackUnit, Unit targetUnit, List<Unit> existingUnitList) {

        // Ваше решение

        System.out.println("getTargetPath started");

        String attackUnitOut;
        attackUnitOut = attackUnit.getName() + "\t("+ attackUnit.getxCoordinate() + ", " + attackUnit.getyCoordinate() + ")";
        String targetUnitOut;
        targetUnitOut = targetUnit.getName() + "\t("+ targetUnit.getxCoordinate() + ", " + targetUnit.getyCoordinate() + ")";

        System.out.println();
        System.out.println(attackUnitOut);
        System.out.println(targetUnitOut);
        System.out.println();


        int width = 27, height = 21;
        boolean[][] obstacles = new boolean[width][height];
        boolean[][] obstaclesTranspon = new boolean[height][width];
        boolean[][] battleField = new boolean[height][width];

        Edge targetEdge = new Edge(targetUnit.getxCoordinate(), targetUnit.getyCoordinate());
        Edge currentEdge = new Edge(attackUnit.getxCoordinate(), attackUnit.getyCoordinate());


        for (Unit unit : existingUnitList) {
            if (unit.isAlive()) {
                obstacles[unit.getxCoordinate()][unit.getyCoordinate()] = true;
                obstaclesTranspon[unit.getyCoordinate()][unit.getxCoordinate()] = true;
                battleField[unit.getyCoordinate()][unit.getxCoordinate()] = true;
            }
        }

        obstacles[targetUnit.getxCoordinate()][targetUnit.getyCoordinate()] = false;

//        var3.stream().forEach(unit -> {
//            Unit u = (Unit) unit;
//            String out = u.getName() + "\t x = " + u.getxCoordinate() + "\t y = " + u.getyCoordinate();
//            System.out.println(out);
//        });

//        boolean[][] obstaclesOut = new boolean[width][height];

        String obstaclesOut;
        obstaclesOut = Arrays.deepToString(obstacles).replace("], ", "]\n");
        obstaclesOut = obstaclesOut.replace("[[", "[");
        obstaclesOut = obstaclesOut.replace("]]", "]");
        obstaclesOut = obstaclesOut.replace("true", "X");
        obstaclesOut = obstaclesOut.replace("false", "0");
        System.out.println();
        System.out.println();
//        System.out.println(obstaclesOut);
        System.out.println();
        System.out.println();

        String obstaclesTransponOut;
        obstaclesTransponOut = Arrays.deepToString(obstaclesTranspon).replace("], ", "]\n");
        obstaclesTransponOut = obstaclesTransponOut.replace("[[", "[");
        obstaclesTransponOut = obstaclesTransponOut.replace("]]", "]");
        obstaclesTransponOut = obstaclesTransponOut.replace("true", "1");
        obstaclesTransponOut = obstaclesTransponOut.replace("false", "0");
        System.out.println();
        System.out.println();
//        System.out.println(obstaclesTransponOut);
        System.out.println();
        System.out.println();


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
//        battleFieldOut = battleFieldOut.replace("true", "1");
//        battleFieldOut = battleFieldOut.replace("false", "0");


        System.out.println();
        System.out.println();
        System.out.println(battleFieldOut);
        System.out.println();
        System.out.println();

        PriorityQueue<EdgeDistance> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.getDistance()));
        pq.add(new EdgeDistance(attackUnit.getxCoordinate(), attackUnit.getyCoordinate(), 0));

        Iterator iterator = pq.iterator();


        String pqOut = new String();
        while (iterator.hasNext()) {
            EdgeDistance elem = (EdgeDistance) iterator.next();
            pqOut = "(" + elem.getX() + ", " + elem.getY() + ") dis = " + elem.getDistance() + "\n";
        }
        System.out.println(pqOut);

//        System.out.println("PriorityQueue pq:");
//        System.out.println(pq);
//        System.out.println();
        System.out.println();
        System.out.println();

//        String priorityQueueOut;
//        priorityQueueOut = Arrays.deepToString(pq);
//        System.out.println(priorityQueueOut);


        Map<String, Edge> cameFrom = new HashMap<>();
        boolean[][] visited = new boolean[width][height];
        boolean[][] visitedTranspon = new boolean[height][width];
        char[][] visitedTransponChar = new char[height][width];


        for ( int i = 0 ; i < visitedTranspon.length; i++)
            for ( int j = 0; j < visitedTranspon[i].length; j++)
                visitedTransponChar[i][j] = visitedTranspon[i][j]?'x':' ';


        System.out.println();
        System.out.println("Is ps empty: " + pq.isEmpty());
        System.out.println();
        System.out.println();


//        stopPrint();

        while (!pq.isEmpty()) {
            EdgeDistance current = pq.poll();

//            try {
//                Runtime.getRuntime().exec("cls");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            stopPrint();

//            stopPrint();
            System.out.print("\033\143");


            System.out.println("EdgeDistance current:");
            System.out.println(current);
            System.out.println();

            boolean checkiIf = visited[current.getX()][current.getY()];
            String checkIfOut = "If (visited[current.getX()][current.getY()]): " + checkiIf;
            checkIfOut = checkIfOut + "(" + current.getX() + ", " + current.getY() + ") " + "dis = " + current.getDistance() + "\n";
//            System.out.println();
//            System.out.println(checkIfOut);
//            System.out.println();

            if (visited[current.getX()][current.getY()]) continue;

            visitedTransponChar[attackUnit.getyCoordinate()][attackUnit.getxCoordinate()] = 'A';
            visitedTransponChar[targetUnit.getyCoordinate()][targetUnit.getxCoordinate()] = 'T';

            for ( int i = 0 ; i < battleFieldChar.length; i++)
                for ( int j = 0; j < battleFieldChar[i].length; j++)
                    if( battleFieldChar[i][j]=='x') visitedTransponChar[i][j] = 'U';


            visited[current.getX()][current.getY()] = true;
            visitedTranspon[current.getY()][current.getX()] = true;
            visitedTransponChar[current.getY()][current.getX()] = 'v';

            String visitedTransponCharOut;
            visitedTransponCharOut = Arrays.deepToString(visitedTransponChar).replace("], ", "]\n");
            visitedTransponCharOut = visitedTransponCharOut.replace("[[", "[");
            visitedTransponCharOut = visitedTransponCharOut.replace("]]", "]");

//            System.out.println(visitedTransponCharOut);
//            try {
//                waytKey();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            stopPrint();

//            System.out.println("If (visited[current.getX()][current.getY()]): ");
//            System.out.println(visited[current.getX()][current.getY()]);
//            System.out.println();

            boolean checkSTOP = false;
            boolean checkCurX = false;
            boolean checkCurY = false;
            if(current.getX() == targetUnit.getxCoordinate()) checkCurX = true;
            if(current.getY() == targetUnit.getyCoordinate()) checkCurY = true;
            if(checkCurX && checkCurY) checkSTOP = true;


            String checkSTOPOut = "If (checkCurX && checkCurY): " + checkSTOP;
            checkSTOPOut = checkSTOPOut + "(" + current.getX() + ", " + current.getY() + ") " + "dis = " + current.getDistance() + "\n";
//            System.out.println();
            System.out.println(checkSTOPOut);
            System.out.println();


            if (current.getX() == targetUnit.getxCoordinate() && current.getY() == targetUnit.getyCoordinate()) {
                List<Edge> path = new ArrayList<>();
                String key = current.getX() + "," + current.getY();

                while (cameFrom.containsKey(key)) {
                    Edge edge = cameFrom.get(key);
                    path.add(0, edge);
                    key = edge.getX() + "," + edge.getY();
                }

                path.add(new Edge(targetUnit.getxCoordinate(), targetUnit.getyCoordinate()));

                System.out.println("getTargetPath: path is founded");
                return path;
            }

            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

            String directionsOut;
            directionsOut = Arrays.deepToString(directions).replace("], ", "]\n");
//            System.out.println(directionsOut);

            for (int[] nextDirection :  directions) {


                String nextDirectionOut;
                nextDirectionOut = Arrays.toString(nextDirection);
//                System.out.println(nextDirectionOut);


                int nx = current.getX() + nextDirection[0], ny = current.getY() + nextDirection[1];
                if (nx < 0 || ny < 0 || nx >= width || ny >= height || obstacles[nx][ny]) continue;

                String key = nx + "," + ny;
                if (!visited[nx][ny] && !cameFrom.containsKey(key)) {
                    pq.add(new EdgeDistance(nx, ny, current.getDistance() + 1));
                    cameFrom.put(key, new Edge(current.getX(), current.getY()));
                }

//               try {
//                waytKey();
//                } catch (IOException e) {
//                throw new RuntimeException(e);
//                }
            }

            Iterator newIterator = pq.iterator();


            pqOut = "";
            while (newIterator.hasNext()) {
                EdgeDistance elem = (EdgeDistance) newIterator.next();
                pqOut = pqOut + "(" + elem.getX() + ", " + elem.getY() + ") dis = " + elem.getDistance() + "\n";
            }
            System.out.println("Очередная очередь: size " + pq.size() + " id " + pq.hashCode() + "\n" + pqOut);


//            stopPrint();
//            try {
//                waytKey();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }
        System.out.println("getTargetPath: path is NOT founded");
        return new ArrayList<>(); // Путь не найден    }
    }



    public void waytKey() throws IOException {
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Not used in this example
            }

            @Override
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                int keyCode = e.getKeyCode();

                System.out.println("Key Pressed: " + keyChar + " (KeyCode: " + keyCode + ")");
                return;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not used in this example
            }
        };
        char tmp;
        //tmp = (char) System.in.read();
        //tmp = (char) new InputStreamReader(System.in).read ();
        tmp = (char) System.console().reader().read();
        if (tmp == ' ') return;

    }

    public int calcDistance(Edge targetEdge, Edge currentEdge){
        int distance;
        distance = Math.abs((targetEdge.getX() - currentEdge.getX())) +
                Math.abs((targetEdge.getY() - currentEdge.getY()));
        return distance;
    }



    public void stopPrint(){
        int inChar;
        String s = "";
        try {
            inChar = System.in.read();
            while (System.in.available() > 0) {
                s += (char) inChar;
                inChar = System.in.read();
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
    }

    public void printVisited(boolean[][] visitedTranspon){

        char[][] visitedTransponChar = new char[visitedTranspon.length][visitedTranspon[0].length];

        for ( int i = 0 ; i < visitedTransponChar.length; i++)
            for ( int j = 0; j < visitedTransponChar[i].length; j++)
                visitedTransponChar[i][j] = visitedTranspon[i][j]?'x':' ';

//        battleFieldChar[attackUnit.getyCoordinate()][attackUnit.getxCoordinate()] = 'A';
//        battleFieldChar[targetUnit.getyCoordinate()][targetUnit.getxCoordinate()] = 'T';
//
//        String battleFieldOut;
//        battleFieldOut = Arrays.deepToString(battleFieldChar).replace("], ", "]\n");
//        battleFieldOut = battleFieldOut.replace("[[", "[");
//        battleFieldOut = battleFieldOut.replace("]]", "]");
    }

}
