package com.tictactoe.ai;

import com.tictactoe.core.Board;
import com.tictactoe.core.WinChecker;

import java.util.Random;

public class ComputerPlayer {

    private Difficulty difficulty = Difficulty.EASY;
    private final Random random = new Random();
    private final WinChecker winChecker = new WinChecker();

    private int lastMoveRow;
    private int lastMoveCol;

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void makeMove(Board board, char symbol) {
        if (difficulty == Difficulty.HARD) {
            if (tryWinOrBlock(board, symbol)) return;
        }
        makeRandomMove(board, symbol);
    }

    private boolean tryWinOrBlock(Board board, char symbol) {
        char opponent = (symbol == 'X') ? 'O' : 'X';

        if (findWinningMove(board, opponent)) {
            board.makeMove(lastMoveRow, lastMoveCol, symbol);
            return true;
        }

        if (findWinningMove(board, symbol)) {
            board.makeMove(lastMoveRow, lastMoveCol, symbol);
            return true;
        }

        return false;
    }

    private boolean findWinningMove(Board board, char symbol) {
        int size = board.getSize();
        char[][] grid = board.getBoard();

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c] == '-') {
                    board.makeMove(r, c, symbol);
                    if (winChecker.hasWon(board, symbol)) {
                        lastMoveRow = r;
                        lastMoveCol = c;
                        board.undoMove(r, c);
                        return true;
                    }
                    board.undoMove(r, c);
                }
            }
        }
        return false;
    }

    public void makeRandomMove(Board board, char symbol) {
        int size = board.getSize();
        int row, col;

        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while (!board.makeMove(row, col, symbol));
    }
}
