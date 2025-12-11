package com.tictactoe;

import com.tictactoe.ai.ComputerPlayer;
import com.tictactoe.ai.Difficulty;
import com.tictactoe.core.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void shouldMakeWinningMoveWhenPossible() {
        Board board = new Board(3);
        ComputerPlayer cpu = new ComputerPlayer();
        cpu.setDifficulty(Difficulty.HARD);

        board.makeMove(0, 0, 'O');
        board.makeMove(0, 1, 'O');

        cpu.makeMove(board, 'O');

        assertEquals('O', board.getBoard()[0][2]);
    }

    @Test
    void shouldBlockOpponentWinningMove() {
        Board board = new Board(3);
        ComputerPlayer cpu = new ComputerPlayer();
        cpu.setDifficulty(Difficulty.HARD);

        board.makeMove(2, 0, 'X');
        board.makeMove(2, 1, 'X');

        cpu.makeMove(board, 'O');

        assertEquals('O', board.getBoard()[2][2], "AI should block X at (2,2)");
    }

    @Test
    void shouldReturnFalseForIncompleteLine() {
        Board board = new Board(3);
        board.makeMove(0,0,'X');
        board.makeMove(0,1,'X');

        assertFalse(board.checkWin('X'));
    }

    @Test
    void shouldNotDetectWinIfLessThanFiveInRowOn10x10() {
        Board board = new Board(10);

        board.makeMove(5,0,'X');
        board.makeMove(5,1,'X');
        board.makeMove(5,2,'X');
        board.makeMove(5,3,'X');

        assertFalse(board.checkWin('X'));
    }

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