package tictactoe;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.placeChessAt(1, 1, "X");
        game.placeChessAt(1, 2, "O");
        game.placeChessAt(1, 3, "X");

        game.placeChessAt(2, 1, "O");
        game.placeChessAt(2, 2, "X");
        game.placeChessAt(2, 3, "O");

        game.placeChessAt(3, 1, "X");
        game.placeChessAt(3, 2, "X");
        game.placeChessAt(3, 3, "O");

        game.displayBoard();
    }
}


class Game {
    private String[][] board;
    private static int width = 3;
    private static int height = 3;

    public Game() {
        board = new String[height][width];
    }

    public void placeChessAt(int row, int col, String chess) {
        board[row - 1][col - 1] = chess;
    }

    public void displayBoard() {
        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            for (int colIndex = 0; colIndex < width; colIndex++) {
                if (colIndex != 0) {
                    System.out.print(" ");
                }
                System.out.print(board[rowIndex][colIndex]);
            }
            System.out.println();
        }
    }
}
