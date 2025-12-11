package com.tictactoe.save;

import java.io.*;

public class SaveGameService {

    public void saveGame(String username, GameState state) {
        File saveFile = new File("save_game_" + username + ".dat");

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            outputStream.writeObject(state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameState loadGame(String username) {
        File saveFile = new File("save_game_" + username + ".dat");

        if (!saveFile.exists()) return null;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(saveFile))) {
            return (GameState) inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}