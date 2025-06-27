package Sudoku;

public class SudokuSolver {
    private final ISudokuGrid grid;

    public SudokuSolver(ISudokuGrid grid)
    {
        this.grid = grid;
    }

    public boolean isSolvable()
    {
        SudokuGrid clonedGrid = ((SudokuGrid) grid).cloneGrid();
        SudokuSolver clonedSolver = new SudokuSolver(clonedGrid);
        return clonedSolver.solveGrid(0, 0);
    }

    public boolean hasUniqueSolution()
    {
        SudokuGrid clonedGrid = ((SudokuGrid) grid).cloneGrid();
        SudokuSolver clonedSolver = new SudokuSolver(clonedGrid);
        return clonedSolver.countSolutions(0, 0, 0) == 1;
    }

    public boolean solveGrid(int row, int col)
    {
        if (row == 9) {
            return true; // Reached past the last row => puzzle solved
        }

        // Compute next cell's coordinates
        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col + 1) % 9;

        if (grid.getValue(row, col) != 0) {
            return solveGrid(nextRow, nextCol); // Skip filled cell
        }

        for (int num = 1; num <= 9; num++) {
            if (isValidMove(row, col, num)) {
                grid.setValue(row, col, num);
                if (solveGrid(nextRow, nextCol)) {
                    return true;
                }
                grid.setValue(row, col, 0); // Backtrack
            }
    }

    return false; // No valid number found
    }

    private int countSolutions(int row, int col, int count)
    {
        if (row == 9)
        {
            return count + 1;
        }

        if (count > 1)
        {
            // More than one solution.
            return count;
        }

        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col + 1) % 9;    

        if (grid.getValue(row, col) != 0) {
            return countSolutions(nextRow, nextCol, count);
        }

        for (int num = 1; num <= 9; num++) {
            if (isValidMove(row, col, num)) {
                grid.setValue(row, col, num);
                count = countSolutions(nextRow, nextCol, count);
                grid.setValue(row, col, 0); // Backtrack

                if (count > 1) {
                    break;
                }
            }
        }

        return count;
    }

    private boolean isValidMove(int row, int col, int num)
    {
        // Create a deep copy of the grid
        int[][] originalGrid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                originalGrid[i][j] = grid.getValue(i, j);
            }
        }

        SudokuGrid newGrid = new SudokuGrid();
        newGrid.initialiseGrid(originalGrid);

        // Make the move
        newGrid.setValue(row, col, num);

        // Validate the move
        return newGrid.isValid();
    }
}
