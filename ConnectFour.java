
// GitHub change comment

import java.util.Scanner;
public class ConnectFour {

    public static void printBoard(char[][] array) {
        for (int r = array.length - 1; r >= 0; r--) { // Begins from array height, so row 0 is on bottom
            for (int c = 0; c < array[r].length; c++) { // For loops go through all array locations
                    System.out.print(array[r][c]); // Prints value at selected location
            }
            System.out.println();
        }
    }

    public static void initializeBoard(char[][] array) {
        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < array[r].length; c++) { // For loops go through all array locations
                array[r][c] = '-'; // Assigns '-' at selected location
            }
        }
    }

    public static int insertChip(char[][] array, int col, char chipType) {
        int r = array.length - 1; // Assign to ensure value to return
        for (int row = 0; row < array.length; row++) { // Goes through all positions in selected column starting from row 0
            r = row;
            if (array[row][col] == '-') { // Look for open position in selected row
                array[row][col] = chipType;
                break; // Leave for loop at open row
            }
        }
        return r;
    }

    public static boolean checkIfWinner ( char[][] array, int col, int row, char chipType) {
        for (int c = 0; c < array[row].length; c++) {
            for (int r = 0; r < array.length - 3; r++) { // Checks if player won vertically
                if (array[r][c] == chipType && array[r + 1][c] == chipType && array[r + 2][c] == chipType && array[r + 3][c] == chipType) { // Four in a col is same value
                    return true;
                }
            }
        }
        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < array[r].length - 3; c++) { // Checks if player won horizontally
                if (array[r][c] == chipType && array[r][c + 1] == chipType && array[r][c + 2] == chipType && array[r][c + 3] == chipType) { // Four in a row is same value
                    return true;
                }
            }
        }
        return false; // Player did not win
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Declare variables
        boolean winner = false;
        char chipType;
        int col, r, count = 0;

        System.out.print("What would you like the height of the board to be? ");
        int height = scanner.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int length = scanner.nextInt();

        char[][] board = new char[height][length];
        initializeBoard(board);
        printBoard(board);


        System.out.println("Player 1: x\nPlayer 2: o");

        while (!winner) {

            chipType = 'x'; // Assign chipType to corresponding player
            System.out.print("Player 1: Which column would you like to choose? "); // Player chooses which column to put chip in
            col = scanner.nextInt();
            r = insertChip(board, col, chipType); // insert chip at lowest row of chosen column
            printBoard(board); // Display board
            winner = checkIfWinner(board, col, r, chipType); // Looks at columns and rows for 4 in row of player's chip
            count++; // Count to determine if tied

            if (winner) { // checkIfWinner returned true
                System.out.println("Player 1 won the game!");
                break;
            }
            else if (count == (height*length)) { // Total chips played = number of available spaces
                System.out.println("Draw. Nobody wins.");
                break;
            }

            // Repeats above process for player 2
            chipType = 'o';
            System.out.print("Player 2: Which column would you like to choose? ");
            col = scanner.nextInt();
            r = insertChip(board, col, chipType);
            printBoard(board);
            winner = checkIfWinner(board, col, r, chipType);
            count++;

            if (winner) {
                System.out.println("Player 2 won the game!");
                break;
            }
            else if (count == (height*length)) {
                System.out.println("Draw. Nobody wins.");
                break;
            }

        }

    }
}
