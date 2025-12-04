package com.tictactoe;

import com.tictactoe.core.Board;
import com.tictactoe.core.MoveValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    void shouldReturnTrueForValidMove() {
        Board board = new Board(3);
        MoveValidator validator = new MoveValidator();

        boolean result = validator.isValid(board, 1, 1);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForOccupiedField() {
        Board board = new Board(3);
        MoveValidator validator = new MoveValidator();

        board.makeMove(0, 0, 'X');
        boolean result = validator.isValid(board, 0, 0);

        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForRowOutOfRange() {
        Board board = new Board(3);
        MoveValidator validator = new MoveValidator();

        boolean result = validator.isValid(board, 3, 1);

        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForColumnOutOfRange() {
        Board board = new Board(3);
        MoveValidator validator = new MoveValidator();

        boolean result = validator.isValid(board, 1, 5);

        assertFalse(result);
    }
}
