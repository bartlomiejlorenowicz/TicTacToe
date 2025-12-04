package com.tictactoe;

import java.util.Random;

public class ComputerPlayer {
    private final Random random = new Random();

    public void makeRandomMove(Board board, char symbol) {
        int size = board.getSize();
        int row, col;

        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while (!board.isFieldEmpty(row, col));

        board.makeMove(row, col, symbol);
        System.out.println("Computer chose: [" + row + ", " + col + "]");
    }
}
