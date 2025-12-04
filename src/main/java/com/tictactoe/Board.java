package com.tictactoe;

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

    public boolean isFieldEmpty(int row, int col) {
        return board[row][col] == '-';
    }

    public boolean makeMove(int row, int col, char symbol) {
        if (row >= 0 && row < size && col >= 0 && col < size && isFieldEmpty(row, col)) {
            board[row][col] = symbol;
            return true;
        }
        return false;
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

}
