import Sudoku.*;

public class App {
    public static void main(String[] args) {
        SudokuGrid grid = new SudokuGrid();
        int[][] board = new int[9][9];
        grid.initialiseGrid(board);
        SudokuGame game = new SudokuGame(grid);
        game.start();
    }
}
