package tictactoe;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Game game = new Game();
        game.displayBoard();
        String currentPlayer = Game.PLAYER_X;


        while (Objects.equals(game.getGameState(), Game.GAME_STATE_NOT_FINISHED)) {
            Position position = requestForCoordinates(game);
            game.placeChessAt(position, currentPlayer);
            game.displayBoard();

            if (currentPlayer.equals(Game.PLAYER_X)) {
                currentPlayer = Game.PLAYER_O;
            } else {
                currentPlayer = Game.PLAYER_X;
            }
        }
        game.displayGameState();

    }

    private static Position requestForCoordinates(Game game) {
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
                if (!Objects.equals(chess, Game.BLANK_CHESS)) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
                break;
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
        return new Position(row, col);
    }
}

class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

class Game {
    public static final String GAME_STATE_IMPOSSIBLE = "Impossible";
    public static final String GAME_STATE_DRAW = "Draw";
    public static final String GAME_STATE_NOT_FINISHED = "Game not finished";
    public static final String GAME_STATE_O_WIN = "O wins";
    public static final String GAME_STATE_Y_WIN = "X wins";
    public static final String PLAYER_X = "X";
    public static final String PLAYER_O = "O";
    public static final String BLANK_CHESS = "_";

    private String[][] board;
    private static int width = 3;
    private static int height = 3;

    public Game() {
        board = new String[height][width];
        for (int i = 0; i < board.length; i++) {
            String[] row = board[i];
            Arrays.fill(row, Game.BLANK_CHESS);
        }
    }

    public void parseBoardText(String boardText) {
        char[] chars = boardText.toCharArray();
        for (int i = 0; i < boardText.length(); i++) {
            int rowIndex = i / width;
            int colIndex = i % width;
            board[rowIndex][colIndex] = Character.toString(chars[i]);
        }
    }


    public void placeChessAt(Position position, String chess) {
        placeChessAt(position.getRow(), position.getCol(), chess);
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
        int countOfX = theNumberOfWinner(PLAYER_X);
        int countOfO = theNumberOfWinner(PLAYER_O);


        int x = getNumberOf(PLAYER_X);
        int y = getNumberOf(PLAYER_O);
        if (Math.abs(x - y) >= 2 || countOfX + countOfO > 1) {
            return GAME_STATE_IMPOSSIBLE;
        }


        if (countOfO == 0 && countOfX == 0) {
            if (getNumberOf(BLANK_CHESS) == 0) {
                return GAME_STATE_DRAW;
            } else {
                return GAME_STATE_NOT_FINISHED;
            }
        }


        if (countOfO == 1) {
            return GAME_STATE_O_WIN;
        } else {
            return GAME_STATE_Y_WIN;
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

    public void displayGameState() {
        System.out.println(getGameState());
    }
}
