package com.tictactoe;

import java.util.Scanner;

public class Game {
    private final Board board;
    private Player currentPlayer = Player.X;
    private final Scanner scanner = new Scanner(System.in);

    public Game(int size) {
        this.board = new Board(size);
    }

    public void start() {
        while (true) {
            board.printBoard();
            System.out.println("Player move: " + currentPlayer);

            System.out.print("Enter row: ");
            int row = scanner.nextInt();
            System.out.print("Enter column: ");
            int col = scanner.nextInt();

            if (!board.makeMove(row, col, currentPlayer.getSymbol())) {
                System.out.println("Invalid move! or cell is not empty.");
                continue;
            }

            if (checkWin(currentPlayer.getSymbol())) {
                board.printBoard();
                System.out.println("Player win " + currentPlayer + "!");
                break;
            }

            if (isBoardFull()) {
                board.printBoard();
                System.out.println("Draw!");
                break;
            }

            switchPlayer();
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    private boolean isBoardFull() {
        char[][] grid = board.getBoard();
        for (char[] row : grid)
            for (char cell : row)
                if (cell == '-') return false;
        return true;
    }

    private boolean checkWin(char symbol) {
        int size = board.getSize();
        char[][] b = board.getBoard();

        for (int i = 0; i < size; i++) {
            if (b[i][0] == symbol && b[i][1] == symbol && b[i][2] == symbol) return true;
            if (b[0][i] == symbol && b[1][i] == symbol && b[2][i] == symbol) return true;
        }

        if (b[0][0] == symbol && b[1][1] == symbol && b[2][2] == symbol) return true;
        if (b[0][2] == symbol && b[1][1] == symbol && b[2][0] == symbol) return true;

        return false;
    }
}
