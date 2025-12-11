package com.tictactoe.ui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RankingRow {

    private final StringProperty username;
    private final IntegerProperty played;
    private final IntegerProperty won;
    private final StringProperty winPercentage;

    public RankingRow(String username, int played, int won) {
        this.username = new SimpleStringProperty(username);
        this.played = new SimpleIntegerProperty(played);
        this.won = new SimpleIntegerProperty(won);

        double percent = played == 0 ? 0 : ((double) won / played) * 100.0;
        this.winPercentage = new SimpleStringProperty(String.format("%.1f%%", percent));
    }

    public StringProperty usernameProperty() { return username; }
    public IntegerProperty playedProperty() { return played; }
    public IntegerProperty wonProperty() { return won; }
    public StringProperty winPercentageProperty() { return winPercentage; }
}
