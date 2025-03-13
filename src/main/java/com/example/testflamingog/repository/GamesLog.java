package com.example.testflamingog.repository;

import com.example.testflamingog.domain.Game;

public interface GamesLog {
    void persistGame(String gameId, Game game);

    Game getGameById(String gameId);
}
