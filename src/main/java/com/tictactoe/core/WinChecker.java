package com.tictactoe.core;

public class WinChecker {

    public boolean hasWon(Board board, char symbol) {
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

    private boolean checkLine(char[][] board,
                              int startRow,
                              int startCol,
                              int dRow,
                              int dCol,
                              int length,
                              char symbol) {

        for (int i = 0; i < length; i++) {
            int r = startRow + i * dRow;
            int c = startCol + i * dCol;

            if (board[r][c] != symbol) {
                return false;
            }
        }
        return true;
    }
}