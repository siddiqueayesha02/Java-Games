import java.util.Scanner;

public class SudokuGame {
    public static void main(String[] args) {
        int[][] puzzle = generateSudokuPuzzle();
        int[][] solution = new int[9][9];
        copyArray(puzzle, solution);

        System.out.println("Welcome to Sudoku!");
        System.out.println("Enter row, column, and value (e.g., '3 5 7') or '0 0 0' to quit.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printSudokuBoard(puzzle);
            System.out.print("Enter your move: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int value = scanner.nextInt();

            if (row == 0 && col == 0 && value == 0) {
                System.out.println("Goodbye!");
                break;
            }

            if (isValidMove(puzzle, row, col, value)) {
                puzzle[row - 1][col - 1] = value;
                if (isSudokuSolved(puzzle)) {
                    System.out.println("Congratulations! You've solved the Sudoku puzzle!");
                    break;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    // Generate a Sudoku puzzle (you can replace this with your own puzzle generation logic)
    private static int[][] generateSudokuPuzzle() {
        int[][] puzzle = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        return puzzle;
    }

    // Copy the contents of one 2D array to another
    private static void copyArray(int[][] source, int[][] destination) {
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[0].length; j++) {
                destination[i][j] = source[i][j];
            }
        }
    }

    // Check if a move is valid
    private static boolean isValidMove(int[][] puzzle, int row, int col, int value) {
        if (row < 1 || row > 9 || col < 1 || col > 9 || value < 1 || value > 9) {
            return false;
        }

        // Check row and column
        for (int i = 0; i < 9; i++) {
            if (puzzle[row - 1][i] == value || puzzle[i][col - 1] == value) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int subgridStartRow = (row - 1) / 3 * 3;
        int subgridStartCol = (col - 1) / 3 * 3;
        for (int i = subgridStartRow; i < subgridStartRow + 3; i++) {
            for (int j = subgridStartCol; j < subgridStartCol + 3; j++) {
                if (puzzle[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    // Check if the Sudoku puzzle is solved
    private static boolean isSudokuSolved(int[][] puzzle) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // Print the Sudoku board
    private static void printSudokuBoard(int[][] puzzle) {
        System.out.println("Sudoku Board:");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("-----------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(puzzle[i][j] == 0 ? ". " : puzzle[i][j] + " ");
            }
            System.out.println();
        }
    }
}
