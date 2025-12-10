package com.tictactoe.ui;

import com.tictactoe.ai.Difficulty;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button play3x3 = new Button("Play 3x3");
        Button play10x10 = new Button("Play 10x10");

        play3x3.setStyle("-fx-font-size: 24;");
        play10x10.setStyle("-fx-font-size: 24;");

        play3x3.setOnAction(e -> startGame(primaryStage, 3, Difficulty.EASY));
        play10x10.setOnAction(e -> startGame(primaryStage, 10, Difficulty.HARD));

        VBox layout = new VBox(20, play3x3, play10x10);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 800, 900);
        primaryStage.setTitle("Tic Tac Toe - Select Mode");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startGame(Stage stage, int size, Difficulty difficulty) {
        TicTacToeApp game = new TicTacToeApp(size);
        game.setDifficulty(difficulty);
        try {
            game.start(stage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
