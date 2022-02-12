package app;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class World implements Movable, Runnable{
    private char[][] map;

    private Semaphore sem = new Semaphore(1);

    public World(char[][] map) {
        this.map = map;
    }

    public boolean addRobot(Robot robot, int row, int col) throws InterruptedException {
        // guard the access
        sem.acquire();
        this.map[row][col] = robot.getId();
        robot.setCurrentRow(row);
        robot.setCurrentCol(col);
        sem.release();
        return true;
    }

    public void run() {
        while(!Thread.interrupted()) {
            try {
                this.display();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void display() throws InterruptedException {
        for(int i = 0; i < 2; i++) {
            System.out.println("\n");
        }
        // accessing map data structure
        // guard before access
        sem.acquire();
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
        sem.release();
    }

    @Override
    public boolean move(int fromRow, int fromCol, int row, int col) throws InterruptedException {
        this.sem.acquire();
        if(row < 0 || col < 0 ||
           row >= 8 || col >= 8 || this.map[row][col] != '1' )
        {
            this.sem.release();
            return false;
        }

        this.map[row][col] = this.map[fromRow][fromCol];
        this.map[fromRow][fromCol] = '1';
        this.sem.release();
        return true;
    }
}
