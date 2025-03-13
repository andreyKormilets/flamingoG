package com.example.testflamingog.contrillers;

import com.example.testflamingog.contrillers.dto.GameStateResponse;
import com.example.testflamingog.service.GamesManagmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameStateController {
    final GamesManagmentService gamesManagmentService;

    public GameStateController(GamesManagmentService gamesManagmentService) {
        this.gamesManagmentService = gamesManagmentService;
    }

    @GetMapping("/games/{gameId}")
    public GameStateResponse getCurrentState(@PathVariable String gameId){
        try {
            return new GameStateResponse(gamesManagmentService.getGameState(gameId));
        } catch (Exception e) {//TODO
            return new GameStateResponse(null);//TODO
        }
    }
}
