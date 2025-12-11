package com.tictactoe.stats;

import java.io.Serializable;

public class PlayerStats implements Serializable {
    private int gamesPlayed;
    private int gamesWon;

    public void recordGame(boolean won) {
        gamesPlayed++;
        if (won) gamesWon++;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }
}
