package com.tictactoe.ui;

import com.tictactoe.ai.ComputerPlayer;
import com.tictactoe.ai.Difficulty;
import com.tictactoe.core.Board;
import com.tictactoe.core.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {

    private Board board;
    private int boardSize;
    private Player currentPlayer = Player.X;
    private Button[][] buttons;
    private final ComputerPlayer computerPlayer = new ComputerPlayer();
    private Label statusLabel = new javafx.scene.control.Label("Your move (X)");

    public TicTacToeApp(int size) {
        this.boardSize = size;
    }

    public TicTacToeApp() {
        this.boardSize = 3;
    }

    public void setDifficulty(Difficulty difficulty) {
        computerPlayer.setDifficulty(difficulty);
    }

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

        board = new Board(boardSize);
        int size = board.getSize();
        int cellSize = (size == 3) ? 100 : 40;
        buttons = new Button[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                Button btn = new Button("-");

                btn.setMinSize(cellSize, cellSize);
                btn.setPrefSize(cellSize, cellSize);

                btn.setStyle("-fx-font-size: " + (size == 3 ? "32" : "12") + ";");

                buttons[row][col] = btn;

                final int r = row;
                final int c = col;

                btn.setOnAction(e -> onCellClicked(btn, r, c));
                grid.add(btn, col, row);
            }
        }

        statusLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-font-size: 18;");
        resetButton.setOnAction(e -> resetGame());

        VBox root = new VBox(15, statusLabel, grid, resetButton);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, size * cellSize + 120, size * cellSize + 250);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(size * cellSize + 140);
        primaryStage.setMinHeight(size * cellSize + 280);

        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void onCellClicked(Button btn, int row, int col) {
        if (!board.makeMove(row, col, currentPlayer.getSymbol())) {
            updateStatus("Invalid move!");
            return;
        }

        refreshButtonsFromBoard();

        if (board.checkWin(Player.X.getSymbol())) {
            updateStatus("You win!");
            disableAllButtons();
            return;
        }

        if (isBoardFull()) {
            updateStatus("Draw!");
            return;
        }

        currentPlayer = Player.O;
        updateStatus("Computer move...");
        computerMove();
    }

    private boolean isBoardFull() {
        char[][] grid = board.getBoard();
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == '-') return false;
            }
        }
        return true;
    }

    private void disableAllButtons() {
        int size = board.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                buttons[row][col].setDisable(true);
            }
        }
    }

    private void computerMove() {
        computerPlayer.makeMove(board, Player.O.getSymbol());
        refreshButtonsFromBoard();

        if (board.checkWin(Player.O.getSymbol())) {
            updateStatus("Computer wins!");
            disableAllButtons();
            return;
        }

        if (isBoardFull()) {
            updateStatus("Draw!");
            return;
        }

        currentPlayer = Player.X;
        updateStatus("Your move (X)");
    }

    private void refreshButtonsFromBoard() {
        int size = board.getSize();
        char[][] grid = board.getBoard();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char value = grid[row][col];

                buttons[row][col].setText(String.valueOf(value));

                buttons[row][col].setDisable(value != '-');
            }
        }
    }

    private void updateStatus(String text) {
        statusLabel.setText(text);
    }

    private void resetGame() {
        board = new Board(boardSize);
        currentPlayer = Player.X;
        updateStatus("Your move (X)");

        int size = board.getSize();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                buttons[row][col].setText("-");
                buttons[row][col].setDisable(false);
            }
        }
    }

}
