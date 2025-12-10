package com.tictactoe.ui;

import com.tictactoe.ai.ComputerPlayer;
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

    private Board board = new Board(3);
    private Player currentPlayer = Player.X;
    private Button[][] buttons = new Button[3][3];
    private final ComputerPlayer computerPlayer = new ComputerPlayer();
    private Label statusLabel = new javafx.scene.control.Label("Your move (X)");


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

                buttons[row][col] = btn;

                final int r = row;
                final int c = col;

                btn.setOnAction(e -> onCellClicked(btn, r, c));

                grid.add(btn, col, row);
            }
        }

        statusLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        VBox root = new VBox(15, statusLabel, grid);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 400, 450);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void onCellClicked(Button btn, int row, int col) {
        if (!board.makeMove(row, col, currentPlayer.getSymbol())) {
            updateStatus("Invalid move!");
            return;
        }

        btn.setText("X");
        btn.setDisable(true);

        if (checkWin(Player.X.getSymbol())) {
            updateStatus("You win!");
            disableAllButtons();
            return;
        }

        if (isBoardFull()) {
            updateStatus("Draw!");
            return;
        }

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
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setDisable(true);
            }
        }
    }

    private boolean checkWin(char symbol) {
        int size = board.getSize();
        int neededToWin = (size == 3) ? 3 : 5;
        char[][] b = board.getBoard();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col <= size - neededToWin; col++) {
                if (checkLine(b, row, col, 0, 1, neededToWin, symbol)) return true;
            }
        }

        for (int col = 0; col < size; col++) {
            for (int row = 0; row <= size - neededToWin; row++) {
                if (checkLine(b, row, col, 1, 0, neededToWin, symbol)) return true;
            }
        }

        for (int row = 0; row <= size - neededToWin; row++) {
            for (int col = 0; col <= size - neededToWin; col++) {
                if (checkLine(b, row, col, 1, 1, neededToWin, symbol)) return true;
            }
        }

        for (int row = 0; row <= size - neededToWin; row++) {
            for (int col = neededToWin - 1; col < size; col++) {
                if (checkLine(b, row, col, 1, -1, neededToWin, symbol)) return true;
            }
        }

        return false;
    }

    private boolean checkLine(char[][] board, int startRow, int startCol,
                              int dRow, int dCol, int length, char symbol) {
        for (int i = 0; i < length; i++) {
            if (board[startRow + i * dRow][startCol + i * dCol] != symbol) {
                return false;
            }
        }
        return true;
    }

    private void computerMove() {
        computerPlayer.makeRandomMove(board, Player.O.getSymbol());
        refreshButtonsFromBoard();

        if (checkWin(Player.O.getSymbol())) {
            updateStatus("Computer wins!");
            disableAllButtons();
            return;
        }

        if (isBoardFull()) {
            updateStatus("Draw!");
            return;
        }

        updateStatus("Your move (X)");
    }

    private void refreshButtonsFromBoard() {
        char[][] grid = board.getBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                char value = grid[row][col];
                if (value == 'O') {
                    buttons[row][col].setText("O");
                    buttons[row][col].setDisable(true);
                }
            }
        }
    }

    private void updateStatus(String text) {
        statusLabel.setText(text);
    }

}
