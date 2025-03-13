package com.example.testflamingog.service.impl;

import com.example.testflamingog.domain.Game;
import com.example.testflamingog.domain.enums.GameStatus;
import com.example.testflamingog.repository.impl.GameRepositoryImpl;
import com.example.testflamingog.service.PlayService;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.example.testflamingog.domain.enums.GameStatus.FAILURE;

@Service
public class PlayServiceImpl implements PlayService {

    private final GameRepositoryImpl gameRepositoryImpl;

    public PlayServiceImpl(GameRepositoryImpl gameRepositoryImpl) {
        this.gameRepositoryImpl = gameRepositoryImpl;
    }

    public GameStatus move(String gameId, int palce, GameStatus currentTurnIsOorX) {
        if (!gameRepositoryImpl.isActive(gameId)) {
            return FAILURE;
        }
        Game game = gameRepositoryImpl.getGameById(gameId);
        GameStatus gameStatus;
        synchronized (game) {
            if (currentTurnIsOorX == game.getGameStatus()) {
                gameStatus = game.addMove(palce);
            } else
                return FAILURE;
        }

        Executors.newSingleThreadScheduledExecutor()
                .schedule(() -> gameRepositoryImpl.finishGame(gameId), 100, TimeUnit.SECONDS);

        return gameStatus;
    }
}
