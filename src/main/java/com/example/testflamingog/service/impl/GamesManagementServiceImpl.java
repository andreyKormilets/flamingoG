package com.example.testflamingog.service.impl;

import com.example.testflamingog.domain.Game;
import com.example.testflamingog.domain.GameStateData;
import com.example.testflamingog.domain.enums.GameStatus;
import com.example.testflamingog.repository.GameRepository;
import com.example.testflamingog.service.GamesManagementService;
import org.springframework.stereotype.Service;

@Service
public class GamesManagementServiceImpl implements GamesManagementService {
    private final GameRepository gameRepository;

    public GamesManagementServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameStateData getGameState(String gameId) {
        Game game = gameRepository.getGameById(gameId);
        return new GameStateData(game.getBoard(), game.getMoves(), game.getGameStatus());
    }

    public GameStatus startGame(String gameId) {
        try {
            gameRepository.addNewGame(gameId, new Game());
        } catch (Exception e) {
            return GameStatus.FAILURE;
        }
        return GameStatus.X_TURN;
    }
}
