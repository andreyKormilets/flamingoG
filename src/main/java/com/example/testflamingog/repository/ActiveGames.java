package com.example.testflamingog.repository;

import com.example.testflamingog.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActiveGames {
    final GamesLog gamesLog;
    private final Map<String, Game> gamesMap =new HashMap<>();

    public ActiveGames(GamesLog gamesLog) {
        this.gamesLog = gamesLog;
    }

    public void addNewGame(String gameId, Game game){
        gamesMap.put(gameId,game);
    }
    public void finishGame(String gameId){
        gamesLog.persistGame(gameId,gamesMap.get(gameId));
        gamesMap.remove(gameId);
    }
    public Game getGameById(String gameId){
        return gamesMap.get(gameId);

    }
    public boolean isActive(String gameId){
        return gamesMap.containsKey(gameId);

    }
}
