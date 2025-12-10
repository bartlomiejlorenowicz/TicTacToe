package com.tictactoe.core;

public class Board {
    private final char[][] board;
    private final int size;

    public Board(int size) {
        this.size = size;
        this.board = new char[size][size];
        fillBoard();
    }

    private void fillBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
    }

    public boolean makeMove(int row, int col, char symbol) {
        if (!isValidMove(row, col)) {
            return false;
        }
        board[row][col] = symbol;
        return true;
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= size) return false;
        if (col < 0 || col >= size) return false;
        return board[row][col] == '-';
    }

    public char[][] getBoard() {
        return board;
    }

    public int getSize() {
        return size;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void undoMove(int row, int col) {
        board[row][col] = '-';
    }

    public boolean checkWin(char symbol) {
        int size = this.size;
        int neededToWin = (size == 3) ? 3 : 5;
        char[][] b = this.board;

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

    private boolean checkLine(char[][] board, int startRow, int startCol,
                              int dRow, int dCol, int length, char symbol) {
        for (int i = 0; i < length; i++) {
            int r = startRow + i * dRow;
            int c = startCol + i * dCol;

            if (r < 0 || r >= this.size || c < 0 || c >= this.size) {
                return false;
            }
            if (board[r][c] != symbol) {
                return false;
            }
        }
        return true;
    }
}
