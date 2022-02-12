package app;

public interface Movable {
    boolean move(int fromRow, int fromCol, int row, int col) throws InterruptedException;
}
