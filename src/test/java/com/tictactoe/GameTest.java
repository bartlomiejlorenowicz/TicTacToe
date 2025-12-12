package com.tictactoe;

import com.tictactoe.core.Board;
import com.tictactoe.core.Game;
import com.tictactoe.core.WinChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private WinChecker winChecker = new WinChecker();

    @Test
    void shouldWinOInRow() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 0, 'O');
        board.makeMove(0, 1, 'O');
        board.makeMove(0, 2, 'O');

        assertTrue(winChecker.hasWon(board, 'O'));
    }

    @Test
    void shouldWinOInColumn() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 1, 'O');
        board.makeMove(1, 1, 'O');
        board.makeMove(2, 1, 'O');

        assertTrue(winChecker.hasWon(board, 'O'));
    }

    @Test
    void shouldWinODiagonalLeftToRight() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 0, 'O');
        board.makeMove(1, 1, 'O');
        board.makeMove(2, 2, 'O');

        assertTrue(winChecker.hasWon(board, 'O'));
    }

    @Test
    void shouldWinODiagonalRightToLeft() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 2, 'O');
        board.makeMove(1, 1, 'O');
        board.makeMove(2, 0, 'O');

        assertTrue(winChecker.hasWon(board, 'O'));
    }

    @Test
    void shouldWinXInRow() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(2, 0, 'X');
        board.makeMove(2, 1, 'X');
        board.makeMove(2, 2, 'X');

        assertTrue(winChecker.hasWon(board, 'X'));
    }

    @Test
    void shouldWinXInColumn() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 0, 'X');
        board.makeMove(1, 0, 'X');
        board.makeMove(2, 0, 'X');

        assertTrue(winChecker.hasWon(board, 'X'));
    }

    @Test
    void shouldWinXDiagonal() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 0, 'X');
        board.makeMove(1, 1, 'X');
        board.makeMove(2, 2, 'X');

        assertTrue(winChecker.hasWon(board, 'X'));
    }

    @Test
    void shouldDetectDraw() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 0, 'X');
        board.makeMove(0, 1, 'O');
        board.makeMove(0, 2, 'X');
        board.makeMove(1, 0, 'O');
        board.makeMove(1, 1, 'O');
        board.makeMove(1, 2, 'X');
        board.makeMove(2, 0, 'O');
        board.makeMove(2, 1, 'X');
        board.makeMove(2, 2, 'O');

        assertFalse(winChecker.hasWon(board, 'X'));
        assertFalse(winChecker.hasWon(board, 'O'));
    }
}