package com.example.testflamingog.service;

import com.example.testflamingog.domain.GameStateData;
import com.example.testflamingog.domain.enums.GameStatus;

public interface GamesManagementService {

    GameStateData getGameState(String gameId);

    GameStatus startGame(String gameId);
}
