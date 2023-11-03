package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private char currentPlayer = 'X';
    private char[][] board = new char[3][3];
    private Button[][] buttons = new Button[3][3];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Крестики-нолики");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setMinSize(100, 100);
                button.setOnAction(e -> {
                    int row = GridPane.getRowIndex(button);
                    int col = GridPane.getColumnIndex(button);
                    makeMove(row, col, button);
                });
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }

        Scene scene = new Scene(gridPane, 320, 320);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void makeMove(int row, int col, Button button) {
        if (board[row][col] == 0) {
            board[row][col] = currentPlayer;
            button.setText(String.valueOf(currentPlayer));

            if (checkWin(currentPlayer)) {
                showWinMessage(currentPlayer);
                resetGame();
            } else if (isBoardFull()) {
                showDrawMessage();
                resetGame();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWin(char player) {
        // Проверка по горизонтали
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // Проверка по вертикали
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }

        // Проверка по диагоналям
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinMessage(char player) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Игра окончена");
        alert.setHeaderText(null);
        alert.setContentText("Игрок " + player + " победил!");
        alert.showAndWait();
    }

    private void showDrawMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Игра окончена");
        alert.setHeaderText(null);
        alert.setContentText("Ничья!");
        alert.showAndWait();
    }

    private void resetGame() {
        currentPlayer = 'X';
        board = new char[3][3];
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setText("");
            }
        }
    }
}