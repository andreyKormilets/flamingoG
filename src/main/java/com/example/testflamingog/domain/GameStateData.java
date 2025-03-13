package com.example.testflamingog.domain;

import com.example.testflamingog.domain.enums.GameStatus;

public record GameStateData(int[] board, int[] moves, GameStatus gameStatus) {
}
