package com.example.testflamingog.contrillers;

import com.example.testflamingog.contrillers.dto.MoveRequest;
import com.example.testflamingog.contrillers.dto.MoveResponse;
import com.example.testflamingog.service.GamesManagementService;
import com.example.testflamingog.service.PlayService;
import com.example.testflamingog.service.impl.PlayServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.testflamingog.domain.enums.GameStatus.FAILURE;


@RestController
public class GameplayController {
    private final GamesManagementService gamesManagementService;
    private final PlayService playService;

    public GameplayController(GamesManagementService gamesManagementService, PlayServiceImpl playService) {
        this.gamesManagementService = gamesManagementService;
        this.playService = playService;
    }

    @PostMapping("/games/{gameId}/move")
    public MoveResponse move(@PathVariable String gameId, @RequestBody MoveRequest move) {
        try {
            return new MoveResponse(playService.move(gameId, move.place(), move.OX()));
        } catch (Exception e) {//TODO
            return new MoveResponse(FAILURE);
        }
    }

    @PostMapping("/games/{gameId}/start")
    public MoveResponse startGame(@PathVariable String gameId) {
        try {
            return new MoveResponse(gamesManagementService.startGame(gameId));
        } catch (Exception e) {//TODO
            return new MoveResponse(FAILURE);
        }
    }
}
