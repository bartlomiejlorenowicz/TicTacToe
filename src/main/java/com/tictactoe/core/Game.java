package com.tictactoe.core;

import com.tictactoe.ai.ComputerPlayer;

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
                int row = getValidNumberFromUser("Enter row: ");
                int col = getValidNumberFromUser("Enter column: ");

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

    private int getValidNumberFromUser(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.next();

            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            }
            System.out.println("Invalid input! Please enter a number.");
        }
    }

    public boolean checkWin(char symbol) {
        int size = board.getSize();
        int neededToWin = (size == 3) ? 3 : 5;
        char[][] b = board.getBoard();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col <= size - neededToWin; col++) {
                if (checkLine(b, row, col, 0, 1, neededToWin, symbol)) return true;
            }
        }

        for (int col = 0; col < size; col++) {
            for (int row = 0; row <= size - neededToWin; row++) {
                if (checkLine(b, row, col, 1, 0, neededToWin, symbol)) return true;
            }
        }

        for (int row = 0; row <= size - neededToWin; row++) {
            for (int col = 0; col <= size - neededToWin; col++) {
                if (checkLine(b, row, col, 1, 1, neededToWin, symbol)) return true;
            }
        }

        for (int row = 0; row <= size - neededToWin; row++) {
            for (int col = neededToWin - 1; col < size; col++) {
                if (checkLine(b, row, col, 1, -1, neededToWin, symbol)) return true;
            }
        }

        return false;
    }

    private boolean checkLine(char[][] board, int startRow, int startCol, int dRow, int dCol, int length, char symbol) {
        for (int i = 0; i < length; i++) {
            if (board[startRow + i * dRow][startCol + i * dCol] != symbol) {
                return false;
            }
        }
        return true;
    }

}
