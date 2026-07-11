import java.util.Scanner;

public class TicTacToe {

    private static final char EMPTY = '-';
    private final char[][] board = new char[3][3];
    private char currentPlayer = 'X';

    public TicTacToe() {
        for (char[] row : board) {
            java.util.Arrays.fill(row, EMPTY);
        }
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---------");
        }
        System.out.println();
    }

    public boolean placeMark(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != EMPTY) {
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true;
            if (board[0][i] != EMPTY && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true;
        }
        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true;
        if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true;
        return false;
    }

    public boolean isBoardFull() {
        for (char[] row : board) {
            for (char c : row) {
                if (c == EMPTY) return false;
            }
        }
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tic-Tac-Toe: enter row and column (0-2) separated by a space, e.g. '1 2'");
        game.printBoard();

        while (true) {
            System.out.printf("Player %c, enter your move: ", game.getCurrentPlayer());
            int row, col;
            try {
                row = scanner.nextInt();
                col = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Enter two integers between 0 and 2.");
                scanner.nextLine();
                continue;
            }

            if (!game.placeMark(row, col)) {
                System.out.println("Invalid move. Cell taken or out of bounds. Try again.");
                continue;
            }

            game.printBoard();

            if (game.checkWin()) {
                System.out.printf("Player %c wins!%n", game.getCurrentPlayer());
                break;
            }

            if (game.isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }

            game.switchPlayer();
        }

        scanner.close();
    }
}