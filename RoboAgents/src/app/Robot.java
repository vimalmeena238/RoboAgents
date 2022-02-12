package app;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Robot implements Runnable {
    private char id;
    private int currentRow;
    private int currentCol;
    private Movable world;
    private int direction = 0;            // 0(up), 1(down), 2(left), 3(right)

    public Robot(char id, Movable w) {
        this.id = id;
        this.world = w;
    }

    public void setId(char id) {
        this.id = id;
    }

    public char getId() {
        return this.id;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }

    private int getNextRow() {
        switch(direction) {
            case 0:
            case 1:
                return currentRow;
            case 2:
                return currentRow - 1;
            case 3:
                return currentRow + 1;
        }
        return currentRow;
    }

    private int getNextCol() {
        switch(direction) {
            case 0:
                return currentCol - 1;
            case 1:
                return currentCol + 1;
            case 2:
            case 3:
                return currentCol;
        }
        return currentCol;
    }
    private void tryToMove() throws InterruptedException {
            int nextRow = this.getNextRow();
            int nextCol = this.getNextCol();
            if(this.world.move(currentRow, currentCol, nextRow, nextCol) ) {
                this.setCurrentRow(nextRow);
                this.setCurrentCol(nextCol);
            }
            else {
                direction = (direction + 1) % 4;
            }
    }
    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                this.tryToMove();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
