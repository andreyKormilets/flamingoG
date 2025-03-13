package com.example.testflamingog.contrillers.dto;

import com.example.testflamingog.domain.enums.GameStatus;

public record MoveRequest(GameStatus OX,
                          Integer place) {
}
