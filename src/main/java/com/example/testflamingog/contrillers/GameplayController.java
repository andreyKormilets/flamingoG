package com.example.testflamingog.contrillers;

import com.example.testflamingog.contrillers.dto.MoveRequest;
import com.example.testflamingog.contrillers.dto.MoveResponse;
import com.example.testflamingog.service.GamesManagmentService;
import com.example.testflamingog.service.PlayService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.testflamingog.domain.enums.GameStatus.FAILURE;


@RestController
public class GameplayController {
    final GamesManagmentService gamesManagmentService;
    final PlayService playService;

    public GameplayController(GamesManagmentService gamesManagmentService, PlayService playService) {
        this.gamesManagmentService = gamesManagmentService;
        this.playService = playService;
    }

    @PostMapping("/games/{gameId}/move")
    public MoveResponse move(@PathVariable String gameId, @RequestBody MoveRequest move){
        try {
            return new MoveResponse(playService.move(gameId,move.place(),move.OX()));
        } catch (Exception e){//TODO
            return new MoveResponse(FAILURE);
        }
    }

    @PostMapping("/games/{gameId}/start")
    public MoveResponse startGame(@PathVariable String gameId){
        try {
            return new MoveResponse(gamesManagmentService.startGame(gameId));
        } catch (Exception e){//TODO
            return new MoveResponse(FAILURE);
        }
    }
}
