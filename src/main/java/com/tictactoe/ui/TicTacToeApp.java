package com.tictactoe.ui;

import com.tictactoe.ai.ComputerPlayer;
import com.tictactoe.ai.Difficulty;
import com.tictactoe.core.Board;
import com.tictactoe.core.Player;
import com.tictactoe.core.WinChecker;
import com.tictactoe.save.GameState;
import com.tictactoe.save.SaveGameService;
import com.tictactoe.stats.RankingService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private final RankingService rankingService = new RankingService();
    private final SaveGameService saveGameService = new SaveGameService();
    private String username = "Player";
    private final WinChecker winChecker = new WinChecker();

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

        GameState loaded = saveGameService.loadGame(username);

        if (loaded != null) {
            restoreBoard(loaded);
            currentPlayer = (loaded.getCurrentPlayer() == 'X') ? Player.X : Player.O;

            Difficulty diff = Difficulty.valueOf(loaded.getDifficulty());
            computerPlayer.setDifficulty(diff);

            updateStatus("Loaded saved game. Current player: " + currentPlayer);
        } else {
            updateStatus("Your move (X)");
        }

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

        Button rankingButton = new Button("Ranking");
        rankingButton.setStyle("-fx-font-size: 18;");
        rankingButton.setOnAction(e -> showRankingWindow());

        Button backToMenuButton = new Button("Back to Menu");
        backToMenuButton.setStyle("-fx-font-size: 18;");
        backToMenuButton.setOnAction(e -> goBackToMenu(primaryStage));

        VBox root = new VBox(15, statusLabel, grid, resetButton, rankingButton , backToMenuButton);

        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 800, 900);
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
        saveCurrentGame();

        if (winChecker.hasWon(board, Player.X.getSymbol())) {
            updateStatus("You win!");
            disableAllButtons();

            rankingService.recordResult(username, true);
            saveGameService.saveGame(username, null);

            return;
        }

        if (isBoardFull()) {
            updateStatus("Draw!");

            rankingService.recordResult(username, false);
            saveGameService.saveGame(username, null);

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
        saveCurrentGame();

        if (winChecker.hasWon(board, Player.O.getSymbol())) {
            updateStatus("Computer wins!");
            disableAllButtons();

            rankingService.recordResult(username, false);
            saveGameService.saveGame(username, null);

            return;
        }

        if (isBoardFull()) {
            updateStatus("Draw!");

            rankingService.recordResult(username, false);
            saveGameService.saveGame(username, null);

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

    private void restoreBoard(GameState state) {
        int size = state.getBoardSize();
        this.boardSize = size;
        this.board = new Board(size);

        char[][] loaded = state.getBoard();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (loaded[r][c] != '-') {
                    board.makeMove(r, c, loaded[r][c]);
                }
            }
        }
        refreshButtonsFromBoard();
    }

    private void saveCurrentGame() {
        GameState state = new GameState(
                board.getBoard(),
                boardSize,
                currentPlayer.getSymbol(),
                computerPlayer.getDifficulty().name()
        );
        saveGameService.saveGame(username, state);
    }

    private void showRankingWindow() {
        Stage stage = new Stage();
        stage.setTitle("Ranking players");

        TableView<RankingRow> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<RankingRow, String> userCol = new TableColumn<>("Username");
        userCol.setCellValueFactory(data -> data.getValue().usernameProperty());

        TableColumn<RankingRow, Integer> playedCol = new TableColumn<>("Played");
        playedCol.setCellValueFactory(data -> data.getValue().playedProperty().asObject());

        TableColumn<RankingRow, Integer> wonCol = new TableColumn<>("Won");
        wonCol.setCellValueFactory(data -> data.getValue().wonProperty().asObject());

        TableColumn<RankingRow, String> percentCol = new TableColumn<>("Win %");
        percentCol.setCellValueFactory(data -> data.getValue().winPercentageProperty());

        table.getColumns().addAll(userCol, playedCol, wonCol, percentCol);

        rankingService.getRanking().forEach((user, stats) -> {
            table.getItems().add(new RankingRow(
                    user,
                    stats.getGamesPlayed(),
                    stats.getGamesWon()
            ));
        });

        VBox root = new VBox(15, table);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 450, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void goBackToMenu(Stage stage) {
        saveGameService.saveGame(username, null);
        StartScreen startScreen = new StartScreen();
        try {
            startScreen.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
