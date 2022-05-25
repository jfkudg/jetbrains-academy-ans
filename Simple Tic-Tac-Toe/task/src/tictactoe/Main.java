package tictactoe;

import javax.print.attribute.standard.NumberOfDocuments;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter cells: ");
        String boardToken = scanner.nextLine();

        Game game = new Game();
        game.parseBoardText(boardToken);
        game.displayBoard();

        int row = 0;
        int col = 0;

        while (true) {
            System.out.print("Enter the coordinates: ");
            String number1 = scanner.next();
            try {
                row = Integer.parseInt(number1);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }
            String number2 = scanner.next();
            try {
                col = Integer.parseInt(number2);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (row >= 1 && row <= 3 && col >= 1 && col <= 3) {
                String chess = game.getChessAt(row, col);
                if (!Objects.equals(chess, "_")) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
                break;
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }

        game.placeChessAt(row, col, "X");
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

    public String getChessAt(int row, int col) {
        return board[row - 1][col - 1];
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

    public String getGameState() {
        int countOfX = theNumberOfWinner("X");
        int countOfO = theNumberOfWinner("O");


        int x = getNumberOf("X");
        int y = getNumberOf("O");
        if (Math.abs(x - y) >= 2 || countOfX + countOfO > 1) {
            return "Impossible";
        }


        if (countOfO == 0 && countOfX == 0) {
            if (getNumberOf("_") == 0) {
                return "Draw";
            } else {
                return "Game not finished";
            }
        }


        if (countOfO == 1) {
            return "O wins";
        } else {
            return "X wins";
        }


    }

    private int getNumberOf(String chess) {
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (Objects.equals(board[i][j], chess)) {
                    count++;
                }
            }
        }
        return count;
    }

    private int theNumberOfWinner(String chess) {
        int count = 0;
        for (int i = 1; i <= width; i++) {
            if (isSameOnRow(i, chess)) {
                count++;
            }
            if (isSameOnCol(i, chess)) {
                count++;
            }
        }
        if (isSameOnMainDiagonal(chess)) {
            count++;
        }
        if (isSameOnSecondaryDiagonal(chess)) {
            count++;
        }
        return count;
    }

    private boolean isSameOnCol(int col, String chess) {
        col--;
        for (int i = 0; i < height; i++) {
            if (!Objects.equals(board[i][col], chess)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSameOnRow(int row, String chess) {
        row--;
        for (String chessText : board[row]) {
            if (!Objects.equals(chess, chessText)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSameOnMainDiagonal(String chess) {
        for (int i = 0; i < 3; i++) {
            if (!Objects.equals(board[i][i], chess)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSameOnSecondaryDiagonal(String chess) {
        for (int i = 0; i < 3; i++) {
            if (!Objects.equals(board[i][width - 1 - i], chess)) {
                return false;
            }
        }
        return true;
    }

    private void displayGameState() {
        System.out.println(getGameState());
    }
}
