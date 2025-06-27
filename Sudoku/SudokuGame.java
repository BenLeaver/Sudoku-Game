package Sudoku;
import java.util.Scanner;

public class SudokuGame {
    private final ISudokuGrid grid;
    private final SudokuSolver solver;
    private final Scanner scanner;

    public SudokuGame(ISudokuGrid grid)
    {
        this.grid = grid;
        this.solver = new SudokuSolver(grid);
        this.scanner = new Scanner(System.in);
    }

    public void start()
    {
        System.out.println(
            "Welcome to the Sudoku Tester!" +
            "======================\n" +
            "Choose an option:\n" +
            "1. Generate Sudoku Puzzle\n" +
            "2. Solve Sudoku Puzzle\n" + 
            "3. Play Sudoku (Command Line)\n" +
            "4. Exit\n" +
            "======================\n"
            );
        int choice = -1;
        while (choice != 4)
        {
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter difficulty level (number of cells to remove): ");
                    int toRemove = Integer.parseInt(scanner.nextLine());
                    SudokuGenerator gen = new SudokuGenerator(grid);
                    gen.generatePuzzle(toRemove);
                    grid.displayGrid();
                    System.out.println("Grid Generated!");
                }
                case 2 -> solvePuzzle();
                case 3 -> getInput();
                default -> {
                }
            }
        }
    }

    public void getInput()
    {
        int choice = -1;
        while (choice != 5)
        {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    displayGrid();
                    getInput();
                }
                case 2 -> {
                    makeMove();
                    getInput();
                }
                case 3 -> {
                    resetGrid();
                    getInput();
                }
                case 4 -> {
                    solvePuzzle();
                    getInput();
                }
                case 5 -> System.out.println("Goodbye!");
                default -> throw new AssertionError();
            }
        }
    }

    private void displayMenu()
    {
        System.out.println(
            "======================\n" +
            "1. Display Sudoku Grid\n" +
            "2. Enter a Number\n" +
            "3. Reset Puzzle\n" + 
            "4. Solve Puzzle\n" +
            "5. Exit\n" +
            "======================\n"
            );
    }

    private void displayGrid()
    {
        grid.displayGrid();
    }

    private void makeMove()
    {
        try{
            System.out.print("Enter row (0-8): ");
            int row = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter column (0-8): ");
            int col = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter number (1-9): ");
            int num = Integer.parseInt(scanner.nextLine());
            
            if (grid.setValue(row, col, num))
            {
                System.out.println("Move placed successfully!");
            }
            else
            {
                System.out.println("Invalid move.");
            }
        }
        catch (NumberFormatException | IndexOutOfBoundsException e)
        {
            System.out.println("Invalid input. Please enter valid numbers.");
        }
    }

    private void resetGrid()
    {
        grid.resetGrid();
        System.out.println("Grid has been reset.");
    }

    private void solvePuzzle()
    {
        if (solver.solveGrid(0, 0))
        {
            System.out.println("Puzzle solved successfully!");
            displayGrid();
        }
        else
        {
            System.out.println("Puzzle cannot be solved.");
        }
    }
}
