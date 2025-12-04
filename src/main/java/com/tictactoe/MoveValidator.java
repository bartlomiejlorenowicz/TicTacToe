package com.tictactoe;

public class MoveValidator {

    public boolean isValid(Board board, int row, int col) {
        if (row < 0 || row >= board.getSize()) return false;
        if (col < 0 || col >= board.getSize()) return false;
        return board.isFieldEmpty(row, col);
    }
}
