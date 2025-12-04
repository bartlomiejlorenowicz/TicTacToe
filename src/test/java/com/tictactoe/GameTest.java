package com.tictactoe;

import com.tictactoe.core.Board;
import com.tictactoe.core.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldWinOInRow() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 0, 'O');
        board.makeMove(0, 1, 'O');
        board.makeMove(0, 2, 'O');

        assertTrue(game.checkWin('O'));
    }

    @Test
    void shouldWinOInColumn() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 1, 'O');
        board.makeMove(1, 1, 'O');
        board.makeMove(2, 1, 'O');

        assertTrue(game.checkWin('O'));
    }

    @Test
    void shouldWinODiagonalLeftToRight() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 0, 'O');
        board.makeMove(1, 1, 'O');
        board.makeMove(2, 2, 'O');

        assertTrue(game.checkWin('O'));
    }

    @Test
    void shouldWinODiagonalRightToLeft() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 2, 'O');
        board.makeMove(1, 1, 'O');
        board.makeMove(2, 0, 'O');

        assertTrue(game.checkWin('O'));
    }

    @Test
    void shouldWinXInRow() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(2, 0, 'X');
        board.makeMove(2, 1, 'X');
        board.makeMove(2, 2, 'X');

        assertTrue(game.checkWin('X'));
    }

    @Test
    void shouldWinXInColumn() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 0, 'X');
        board.makeMove(1, 0, 'X');
        board.makeMove(2, 0, 'X');

        assertTrue(game.checkWin('X'));
    }

    @Test
    void shouldWinXDiagonal() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0, 0, 'X');
        board.makeMove(1, 1, 'X');
        board.makeMove(2, 2, 'X');

        assertTrue(game.checkWin('X'));
    }

    @Test
    void shouldDetectDraw() {
        Game game = new Game(3);
        Board board = game.getBoard();

        board.makeMove(0,0,'X');
        board.makeMove(0,1,'O');
        board.makeMove(0,2,'X');
        board.makeMove(1,0,'O');
        board.makeMove(1,1,'O');
        board.makeMove(1,2,'X');
        board.makeMove(2,0,'O');
        board.makeMove(2,1,'X');
        board.makeMove(2,2,'O');

        assertFalse(game.checkWin('X'));
        assertFalse(game.checkWin('O'));
    }

}