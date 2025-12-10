package com.tictactoe.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class TicTacToeApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button btn = new Button("-");
                btn.setPrefSize(100, 100);
                btn.setStyle("-fx-font-size: 32;");

                final int r = row;
                final int c = col;

                btn.setOnAction(e -> onCellClicked(btn, r, c));

                grid.add(btn, col, row);
            }
        }

        Scene scene = new Scene(grid, 400, 450);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void onCellClicked(Button btn, int row, int col) {
        btn.setText("X");
        btn.setDisable(true);
    }

}
