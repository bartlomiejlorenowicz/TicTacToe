package com.tictactoe.core;

import com.tictactoe.ai.ComputerPlayer;

import java.util.Scanner;

public class Game {
    private final ComputerPlayer computer = new ComputerPlayer();
    private final Board board;
    private Player currentPlayer = Player.X;
    private final Scanner scanner = new Scanner(System.in);
    private final WinChecker winChecker = new WinChecker();

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

                if (!board.makeMove(row, col, currentPlayer.getSymbol())) {
                    System.out.println("Invalid move! Cell occupied or out of range.");
                    continue;
                }

            } else {
                System.out.println("Computer move: O");
                computer.makeRandomMove(board, currentPlayer.getSymbol());
            }

            if (winChecker.hasWon(board, currentPlayer.getSymbol())) {
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
}
