package com.tictactoe.core;

public enum Player {

    X('X'),
    O('O');

    private final char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
