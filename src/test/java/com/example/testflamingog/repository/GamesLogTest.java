package com.example.testflamingog.repository;

import com.example.testflamingog.domain.Game;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamesLogTest {
    GamesLog gamesLog=new GamesLog();

    @Test
    void persistGame() {
        Game game=new Game();

        game.addMove(2);
        game.addMove(3);
        game.addMove(4);
        game.addMove(5);
        game.addMove(6);
        game.addMove(7);

        gamesLog.persistGame("id",game);
        Game loadedGame = gamesLog.getGameById("id");
        for(int i=0;i<9;i++){
            if(game.getMoves()[i]!=loadedGame.getMoves()[i]||game.getBoard()[i]!=loadedGame.getBoard()[i]){
                Assert.fail("on step "+i);
            }

        }

    }

}