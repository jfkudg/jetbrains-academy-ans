package tictactoe;

import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter cells: ");
        String boardToken = scanner.nextLine();

        Game game = new Game();
        game.parseBoardText(boardToken);
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

    public void parseBoardText(String boardText) {
        char[] chars = boardText.toCharArray();
        for (int i = 0; i < boardText.length(); i++) {
            int rowIndex = i / width;
            int colIndex = i % width;
            board[rowIndex][colIndex] = Character.toString(chars[i]);
        }
    }

    public void placeChessAt(int row, int col, String chess) {
        board[row - 1][col - 1] = chess;
    }

    public void displayBoard() {
        System.out.println("---------");
        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            System.out.print("|");
            for (int colIndex = 0; colIndex < width; colIndex++) {
                System.out.print(" ");
                System.out.print(board[rowIndex][colIndex]);
            }
            System.out.print(" |");
            System.out.println();
        }
        System.out.println("---------");
    }
}
