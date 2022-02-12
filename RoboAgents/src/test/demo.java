package test;

import app.*;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class demo {
    public static void main(String[] args) throws InterruptedException {

        char[][] map = new char[8][8];

        map[0] = new char[] {'1','1','1','1','1','1','1','1'};
        map[1] = new char[] {'1','1','0','0','0','0','0','1'};
        map[2] = new char[] {'1','0','0','1','0','1','0','1'};
        map[3] = new char[] {'1','1','0','0','0','1','1','1'};
        map[4] = new char[] {'1','1','1','0','1','1','1','1'};
        map[5] = new char[] {'1','1','1','1','0','1','1','1'};
        map[6] = new char[] {'1','0','1','1','1','1','1','1'};
        map[7] = new char[] {'1','1','1','1','1','1','1','1'};

        World w = new World(map);

        Robot r1 = new Robot('V', (Movable) w);
        Robot r2 = new Robot('B', (Movable) w);
        Robot r3 = new Robot('A', (Movable) w);
        Robot r4 = new Robot('Q', (Movable) w);

        w.addRobot(r1, 1,1);
        w.addRobot(r2, 5,5);
        w.addRobot(r3, 7,7);
        w.addRobot(r4, 0, 7);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(r1);
        executorService.submit(r2);
        executorService.submit(r3);
        executorService.submit(r4);
        executorService.submit(w);
    }
}
