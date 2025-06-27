package Sudoku;
import java.util.HashSet;
import java.util.Set;

public class SudokuGrid implements ISudokuGrid {

    private static final int GRID_SIZE = 9;
    private int[][] grid;

    public SudokuGrid()
    {
        this.grid = new int[GRID_SIZE][GRID_SIZE];
    }

    /**
     * Validates if row and col values are within grid size (i.e. >=0 and <9)
     * @param row
     * @param col
     */
    private void validateIndices(int row, int col)
    {
        if (row < 0 || row >= 9 || col < 0 || col >= 9)
        {
            throw new IndexOutOfBoundsException("Row and column indices must be between 0 and 8.");
        }
    }

    @Override
    public void initialiseGrid(int[][] grid) {
        this.grid = grid;
    }

    @Override
    public int getValue(int row, int col) {
        validateIndices(row, col);
        return grid[row][col];
    }

    @Override
    public boolean setValue(int row, int col, int value) {
        validateIndices(row, col);
        grid[row][col] = value;
        return true;
    }

    @Override
    public boolean isValid() {
        for (int row = 0; row < 9; row++)
        {
            if (!isRowValid(row))
            {
                return false;
            }
        }

        for (int col = 0; col < 9; col++)
        {
            if (!isColValid(col))
            {
                return false;
            }
        }

        for (int i = 0; i < 9; i += 3)
        {
            for (int j = 0; j < 9; j += 3)
            {
                if (!isBoxValid(i, j))
                {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isRowValid(int row)
    {
        Set<Integer> availableNums = new HashSet<>(Set.of(1,2,3,4,5,6,7,8,9));
        for (int i = 0; i < 9; i++)
        {
            if (grid[row][i] != 0)
            {
                if (!availableNums.contains(grid[row][i]))
                {
                    return false;
                }
                availableNums.remove(grid[row][i]);
            }
        }
        return true;
    }

    private boolean isColValid(int col)
    {
        Set<Integer> availableNums = new HashSet<>(Set.of(1,2,3,4,5,6,7,8,9));
        for (int i = 0; i < 9; i++)
        {
            if (grid[i][col] != 0)
            {
                if (!availableNums.contains(grid[i][col]))
                {
                    return false;
                }
                availableNums.remove(grid[i][col]);
            }
        }
        return true;
    }

    private boolean isBoxValid(int topLeftRow, int topLeftCol)
    {
        Set<Integer> availableNums = new HashSet<>(Set.of(1,2,3,4,5,6,7,8,9));
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                int currentValue = grid[topLeftRow+i][topLeftCol+j];
                if (currentValue != 0)
                {
                    if (!availableNums.contains(currentValue))
                    {
                        return false;
                    }
                    availableNums.remove(currentValue);
                }
            }
        }
        return true;
    }

    

    @Override
    public boolean isSolved() {
        return isValid() && !hasEmptyCells();
    }

    private boolean hasEmptyCells()
    {
        for (int[] row : grid)
        {
            for (int cell : row)
            {
                if (cell == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void resetGrid() {
        grid = new int[GRID_SIZE][GRID_SIZE];
    }

    @Override
    public void displayGrid() {
        for (int[] row : grid)
        {
            for (int cell : row)
            {
                if (cell == 0)
                {
                    System.out.print(".");
                }
                else
                {
                    System.out.print(cell);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
