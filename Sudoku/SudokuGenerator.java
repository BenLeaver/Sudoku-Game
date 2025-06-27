package Sudoku;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    private final ISudokuGrid grid;
    private final Random random;

    public SudokuGenerator(ISudokuGrid grid)
    {
        this.grid = grid;
        this.random = new Random();
    }

    /**
     * Puzzle generator with given difficulty.
     * @param difficulty
     */
    public void generatePuzzle(int difficulty)
    {
        generateFullGrid(0, 0);
        removeNumbers(difficulty);
    }

    private boolean generateFullGrid(int row, int col)
    {
        if (row == 9) {
            return true;
        }

        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col + 1) % 9;

        List<Integer> numbers = getShuffledNumbers();

        for (int num : numbers) {
            if (isValidMove(row, col, num)) {
                grid.setValue(row, col, num);
                if (generateFullGrid(nextRow, nextCol)) {
                    return true;
                }
                grid.setValue(row, col, 0);
            }
        }

        return false;
    }


    private void removeNumbers(int difficulty)
    {
        int cellsRemoved = 0;
        List<int[]> cellPositions = new ArrayList<>();

        // Generate all cell positions (0–8, 0–8)
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cellPositions.add(new int[] {row, col});
            }
        }

        // Shuffle cell positions
        Collections.shuffle(cellPositions, random);

        for (int[] pos : cellPositions) {
            if (cellsRemoved >= difficulty) break;

            int row = pos[0];
            int col = pos[1];
            int originalValue = grid.getValue(row, col);
            if (originalValue != 0)
            {
                // Try removing the number
                grid.setValue(row, col, 0);

                SudokuGrid clonedGrid = ((SudokuGrid) grid).cloneGrid();
                SudokuSolver solver = new SudokuSolver(clonedGrid);

                if (solver.hasUniqueSolution()) {
                    cellsRemoved++;
                } else {
                    // Restore if uniqueness is broken
                    grid.setValue(row, col, originalValue);
                }
            }
        }
    }

    private boolean isValidMove(int row, int col, int num)
    {
        int original = grid.getValue(row, col);
        grid.setValue(row, col, num);
        boolean valid = grid.isValid();
        grid.setValue(row, col, original); // Restore
        return valid;
    }

    /**
     * Generates a shuffled list of numbers for random insertion into grid.
     * @return
     */
    private List<Integer> getShuffledNumbers()
    {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, random);
        return numbers;
    }
}
