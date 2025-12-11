package com.tictactoe.save;

import java.io.Serializable;

public class GameState implements Serializable {

    private final char[][] board;
    private final int boardSize;
    private final char currentPlayer;
    private final String difficulty;

    public GameState(char[][] board, int boardSize, char currentPlayer, String difficulty) {
        this.board = board;
        this.boardSize = boardSize;
        this.currentPlayer = currentPlayer;
        this.difficulty = difficulty;
    }

    public char[][] getBoard() {
        return board;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public String getDifficulty() {
        return difficulty;
    }
}