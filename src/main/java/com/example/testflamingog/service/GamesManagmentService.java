package com.example.testflamingog.service;

import com.example.testflamingog.domain.Game;
import com.example.testflamingog.domain.GameStateData;
import com.example.testflamingog.domain.enums.GameStatus;
import com.example.testflamingog.repository.ActiveGames;
import com.example.testflamingog.repository.GamesLog;
import org.springframework.stereotype.Component;

@Component
public class GamesManagmentService {
    final ActiveGames activeGames;
    final GamesLog gamesLog;

    public GamesManagmentService(ActiveGames activeGames, GamesLog gamesLog) {
        this.activeGames = activeGames;
        this.gamesLog = gamesLog;
    }
    public GameStateData getGameState(String gameId){
        Game game;
        if(activeGames.isActive(gameId)){
            game= activeGames.getGameById(gameId);
        } else {
            game=gamesLog.getGameById(gameId);
        }
        return new GameStateData(game.getBoard(), game.getMoves(), game.getGameStatus());    }
    public GameStatus startGame(String gameId){
        try {
            activeGames.addNewGame(gameId,new Game());
        }  catch (Exception e){
            return GameStatus.FAILURE;
        }
        return GameStatus.X_TURN;
    }
}
