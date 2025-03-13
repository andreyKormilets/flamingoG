package com.example.testflamingog.service;

import com.example.testflamingog.domain.Game;
import com.example.testflamingog.domain.enums.GameStatus;
import com.example.testflamingog.repository.ActiveGames;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.example.testflamingog.domain.enums.GameStatus.FAILURE;

@Component
public class PlayService {

    final ActiveGames activeGames;

    public PlayService(ActiveGames activeGames) {
        this.activeGames = activeGames;
    }

    public GameStatus move(String gameId, int palce, GameStatus currentTurnIsOorX ){
        if(!activeGames.isActive(gameId)){
            return FAILURE;
        }
        Game game= activeGames.getGameById(gameId);
        GameStatus gameStatus;
        synchronized (game){
            if(currentTurnIsOorX==game.getGameStatus()){
                gameStatus=  game.addMove(palce);
            } else
                return FAILURE;
        }

        Executors.newSingleThreadScheduledExecutor().schedule(()-> activeGames.finishGame(gameId),100, TimeUnit.SECONDS );

        return gameStatus;
    }
}
