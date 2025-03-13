package com.example.testflamingog.repository;

import com.example.testflamingog.domain.Game;

public interface GameRepository {
    void addNewGame(String gameId, Game game);

    void finishGame(String gameId);

    Game getGameById(String gameId);

    boolean isActive(String gameId);
}
