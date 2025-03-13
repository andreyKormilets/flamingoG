package com.example.testflamingog.repository.impl;

import com.example.testflamingog.domain.Game;
import com.example.testflamingog.repository.GamesLog;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GamesLogImpl implements GamesLog {
    private final Map<String, Integer> gamesMap = new HashMap<>();

    public void persistGame(String gameId, Game game) {
        int[] moves = game.getMoves();
        int persisted = moves[0];
        for (int i = 1; i < game.getMovesCounter(); i++) {
            persisted = persisted * 10;
            persisted = persisted + moves[i];
        }
        persisted = persisted * 10;
        persisted = persisted + game.getMovesCounter();
        gamesMap.put(gameId, persisted);
    }

    public Game getGameById(String gameId) {
        int persisted = gamesMap.get(gameId);
        Game game = new Game();
        int[] moves = new int[9];
        int counter = persisted % 10;
        persisted = persisted / 10;
        for (int i = 0; i < counter; i++) {
            moves[counter - 1 - i] = persisted % 10;
            persisted = persisted / 10;
        }
        for (int i = 0; i < counter; i++) {
            game.addMove(moves[i]);
        }
        return game;
    }
}
