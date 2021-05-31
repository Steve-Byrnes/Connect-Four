import java.util.Scanner;
/*
Stephen Byrnes
112519650
Project
 */

public class ConnectFour {
    private static int[][] board = new int[6][7]; //Yellow Entries = 2, Red Entries = 1

    public static void display(int[][] board) {
        for (int[] ints : board) {
            System.out.print("|");
            for (int j = 0; j < board[0].length; j++) {
                if (ints[j] == 1) {
                    System.out.print("R" + "|");
                } else if (ints[j] == 2) {
                    System.out.print("Y" + "|");
                } else {
                    System.out.print(" " + "|");
                }
            }
            System.out.println();
        }
        System.out.println(".....................");
    }

    public static int turn(int[][] board, int color) {
        if (color == 1) {
            System.out.print("Enter a column for Red's Turn (0 to 6): ");
        } else if (color == 2) {
            System.out.print("Enter a column for Yellow's Turn (0 to 6): ");
        }
        Scanner userInput = new Scanner(System.in);
        int column = userInput.nextInt();

        if (column < 0 || column > 6) {
            System.out.println("Error: Invalid input! Pick another column");
        } else {
            for (int j = 5; j >= 0; j--) {
                if (board[j][column] == 0) {
                    board[j][column] = color;
                    return 0;
                }
            }
        }
        return 1;
    }

    public static boolean winVertical(int[][] board, int color) {
        for (int i = 0; i < board[0].length; i++) {
            int count = 1;
            for (int j = 0; j < board.length - 1; j++) {
                if ((board[j][i] == color) && (board[j + 1][i] == color)) {
                    count++;
                } else {
                    count = 1;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean winHorizontal(int[][] board, int color) {
        for (int[] ints : board) {
            int count = 1;
            for (int j = 0; j < board[0].length - 1; j++) {
                if ((ints[j] == color) && (ints[j + 1] == color)) {
                    count++;
                } else {
                    count = 1;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean winDiagonalDown(int[][] board, int color) {
        for (int k = 0; k < 3; k++) {   //Upper 3 Diagonals
            int count = 1;
            for (int i = 0; i < (5 - k); i++) {
                if (board[i][i + (1 + k)] == color && board[i + 1][(i + 1) + (1 + k)] == color) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                } else {
                    count = 1;
                }
            }
        }
        for (int k = 0; k < 3; k++) {   //Lower 3 Diagonals
            int count = 1;
            for (int i = 0; i < (5 - k); i++) {
                if (board[(i + k)][i] == color && board[(i + k) + 1][i + 1] == color) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                } else {
                    count = 1;
                }
            }
        }
        return false;
    }

    public static boolean winDiagonalUp(int[][] board, int color) {
        for (int k = 0; k < 3; k++) {   //Upper 3 Diagonals
            int count = 1;
            for (int i = 3; i > 0; i--) {
                if (board[i + k][(3 - i)] == color && board[(i + k) - 1][(3 - i) + 1] == color) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                } else {
                    count = 1;
                }
            }
        }
        for (int k = 1; k < 4; k++) {   //Lower 3 Diagonals
            int count = 1;
            for (int i = 5; i >= k; i--) {
                if (board[i][(5 - i) + k] == color && board[i - 1][((5 - i) + 1) + k] == color) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                } else {
                    count = 1;
                }
            }
        }
        return false;
    }

    public static boolean tie(int[][] board) {
        int count = 0;
        for (int[] ints : board) {
            for (int k = 0; k < board[0].length; k++) {
                if (ints[k] == 1 || ints[k] == 2) {
                    count++;
                }
            }
        }
        return count == 42;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Connect-Four!");
        display(board);
        int count = 0;
        while (!(tie(board))) {
            if (count % 2 != 1) {
                while (turn(board, 1) != 0) {}
            } else {
                while (turn(board, 2) != 0) {}
            }
            display(board);
            count++;
            if (winVertical(board, 2) || winHorizontal(board, 2) || winDiagonalUp(board, 2) ||
                    winDiagonalDown(board, 2)) {
                System.out.println("Yellow Wins!");
                break;
            } else if (winVertical(board, 1) || winHorizontal(board, 1) || winDiagonalUp(board, 1) ||
            winDiagonalDown(board, 1)) {
                System.out.println("Red Wins!");
                break;
            } else if (tie(board)) {
                System.out.println("Tie Game!");
                break;
            }
        }
    }
}
