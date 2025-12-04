package com.tictactoe;

import java.util.Scanner;

public class Game {
    private final ComputerPlayer computer = new ComputerPlayer();
    private final MoveValidator validator = new MoveValidator();
    private final Board board;
    private Player currentPlayer = Player.X;
    private final Scanner scanner = new Scanner(System.in);

    public Game(int size) {
        this.board = new Board(size);
    }

    public void start() {
        while (true) {
            board.printBoard();

            if (currentPlayer == Player.X) {
                System.out.println("Player move: X");
                System.out.print("Enter row: ");
                int row = scanner.nextInt();
                System.out.print("Enter column: ");
                int col = scanner.nextInt();

                if (!validator.isValid(board, row, col)) {
                    System.out.println("Invalid move! Cell already occupied or out of range.");
                    continue;
                }
                board.makeMove(row, col, currentPlayer.getSymbol());
            } else {
                System.out.println("Computer move: O");
                computer.makeRandomMove(board, currentPlayer.getSymbol());
            }

            if (checkWin(currentPlayer.getSymbol())) {
                board.printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
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

    public Board getBoard() {
        return board;
    }

    private boolean isBoardFull() {
        char[][] grid = board.getBoard();
        for (char[] row : grid)
            for (char cell : row)
                if (cell == '-') return false;
        return true;
    }

    public boolean checkWin(char symbol) {
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
