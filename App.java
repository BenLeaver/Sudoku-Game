import Sudoku.*;

public class App {
    public static void main(String[] args) {
        SudokuGrid grid = new SudokuGrid();
        int[][] board = new int[9][9];
        grid.initialiseGrid(board);
        grid.displayGrid();
        System.out.println(grid.isValid());
        System.out.println(grid.isSolved());
        grid.setValue(0, 0, 1);
        grid.displayGrid();
        System.out.println(grid.isValid());
        grid.setValue(2, 2, 2);
        grid.displayGrid();
        System.out.println(grid.isValid());
    }
}
