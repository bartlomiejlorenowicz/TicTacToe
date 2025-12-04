package com.tictactoe;

import com.tictactoe.core.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void shouldInitializeBoardWithEmptyFields() {
        Board board = new Board(3);
        char[][] grid = board.getBoard();

        for (char[] row : grid) {
            for (char cell : row) {
                assertEquals('-', cell);
            }
        }
    }

    @Test
    void shouldMakeValidMove() {
        Board board = new Board(3);
        boolean moveResult = board.makeMove(1, 1, 'X');

        assertTrue(moveResult);
        assertEquals('X', board.getBoard()[1][1]);
    }

    @Test
    void shouldRejectMoveOnOccupiedField() {
        Board board = new Board(3);
        board.makeMove(0, 0, 'X');
        boolean secondMoveResult = board.makeMove(0, 0, 'O');

        assertFalse(secondMoveResult);
        assertEquals('X', board.getBoard()[0][0]);
    }

    @Test
    void shouldRejectMoveOutsideBoard() {
        Board board = new Board(3);
        boolean result = board.makeMove(3, 3, 'X');

        assertFalse(result);
    }

}