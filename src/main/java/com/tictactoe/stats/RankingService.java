package com.tictactoe.stats;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RankingService {

    private final File file = new File("ranking.list");
    private Map<String, PlayerStats> ranking = new HashMap<>();

    public RankingService() {
        load();
    }

    public Map<String, PlayerStats> getRanking() {
        return ranking;
    }

    public void recordResult(String username, boolean won) {
        ranking.putIfAbsent(username, new PlayerStats());
        ranking.get(username).recordGame(won);
        save();
    }

    public void save() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(ranking);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        if (!file.exists()) return;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = inputStream.readObject();
            if (obj instanceof HashMap) {
                ranking = (HashMap<String, PlayerStats>) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}