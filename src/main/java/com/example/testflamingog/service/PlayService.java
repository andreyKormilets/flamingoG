package com.example.testflamingog.service;

import com.example.testflamingog.domain.enums.GameStatus;

public interface PlayService {
    GameStatus move(String gameId, int palce, GameStatus currentTurnIsOorX);
}
