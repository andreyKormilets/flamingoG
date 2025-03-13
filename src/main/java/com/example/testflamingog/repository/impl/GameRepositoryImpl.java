package com.example.testflamingog.repository.impl;

import com.example.testflamingog.domain.Game;
import com.example.testflamingog.repository.GameRepository;
import com.example.testflamingog.repository.GamesLog;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameRepositoryImpl implements GameRepository {
    private final GamesLog gamesLog;
    private final Map<String, Game> activeGamesMap = new HashMap<>();

    public GameRepositoryImpl(GamesLog gamesLog) {
        this.gamesLog = gamesLog;
    }

    public void addNewGame(String gameId, Game game) {
        activeGamesMap.put(gameId, game);
    }

    public void finishGame(String gameId) {
        gamesLog.persistGame(gameId, activeGamesMap.get(gameId));
        activeGamesMap.remove(gameId);
    }

    public Game getGameById(String gameId) {
        if (isActive(gameId)) {
            return activeGamesMap.get(gameId);
        } else {
            return gamesLog.getGameById(gameId);
        }
    }

    public boolean isActive(String gameId) {
        return activeGamesMap.containsKey(gameId);
    }
}
