package com.example.testflamingog.contrillers;

import com.example.testflamingog.contrillers.dto.GameStateResponse;
import com.example.testflamingog.service.GamesManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameStateController {
    private final GamesManagementService gamesManagementService;

    public GameStateController(GamesManagementService gamesManagementService) {
        this.gamesManagementService = gamesManagementService;
    }

    @GetMapping("/games/{gameId}")
    public GameStateResponse getCurrentState(@PathVariable String gameId) {
        try {
            return new GameStateResponse(gamesManagementService.getGameState(gameId));
        } catch (Exception e) {//TODO
            return new GameStateResponse(null);//TODO
        }
    }
}
