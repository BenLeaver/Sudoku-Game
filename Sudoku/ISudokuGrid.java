package Sudoku;
public interface ISudokuGrid {
    /**
     * Initialises Soduku Grid.
     * @param grid the 2D array used to initialise the Soduku Grid.
     */
    void initialiseGrid(int[][] grid);

    /**
     * Returns a value from the grid at a specific row and column.
     */
    int getValue(int row, int col);

    /**
     * Sets a value in the grid at a specific row and column.
     * @param row the row of the value to set.
     * @param col the column of the value to set.
     * @param value the new value to be set.
     * @return whether the new value was successfully set.
     */
    boolean setValue(int row, int col, int value);

    /**
     * Checks if the grid state is a valid Sudoku configuration.
     * @return whether the grid is a valid Sudoku configuration.
     */
    boolean isValid();

    /**
     * Checks if Sudoku Grid is fully solved.
     * @return whether the grid is solved.
     */
    boolean isSolved();

    /**
     * Resets the Sudoku Grid to its initial state.
     */
    void resetGrid();

    /**
     * Displays the Sudoku Grid.
     */
    void displayGrid();
}
