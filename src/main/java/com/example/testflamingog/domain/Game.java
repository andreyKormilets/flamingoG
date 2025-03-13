package com.example.testflamingog.domain;

import com.example.testflamingog.domain.enums.GameStatus;

import static com.example.testflamingog.domain.enums.GameStatus.*;

public class Game {
    int[] moves;
    int[] board;
    int movesCounter;
    GameStatus gameStatus;

    public Game(){
        this.moves=new int [9];
        this.board=new int[9];
        this.gameStatus=X_TURN;
    }
    public synchronized GameStatus addMove(int place){
        if(!isValidPlace(place)){
            return GameStatus.FAILURE;
        }
        putOnBoard( place);
        updateMovesList(place);
        gameStatus=newGameStatus();
        return gameStatus;
    }
    public synchronized boolean isCompleated( ){
        return gameStatus== O_WIN || gameStatus==X_WIN || gameStatus== DRAW;
    }

    private boolean isValidPlace(int place){
        return board[place]==0;
    }
    private void putOnBoard(int place){
        if(gameStatus==GameStatus.X_TURN){
            board[place]=1;
        } else {
            board[place]=2;
        }
    }

    private void updateMovesList(int place){
        moves[movesCounter]=place;
        movesCounter++;
    }

    private GameStatus newGameStatus(){ //TODO can be done much better  but  for 3x3 is ok
        boolean isItWin=false;
        if(board[0]!=0&&board[0]==board[1]&&board[1]==board[2]) isItWin=true;
        if(board[3]!=0&&board[3]==board[4]&&board[4]==board[5]) isItWin=true;
        if(board[6]!=0&&board[6]==board[7]&&board[7]==board[8]) isItWin=true;

        if(board[0]!=0&&board[0]==board[3]&&board[3]==board[6]) isItWin=true;
        if(board[1]!=0&&board[1]==board[4]&&board[4]==board[7]) isItWin=true;
        if(board[2]!=0&&board[2]==board[5]&&board[5]==board[8]) isItWin=true;

        if(board[0]!=0&&board[0]==board[4]&&board[4]==board[8]) isItWin=true;
        if(board[2]!=0&&board[2]==board[4]&&board[4]==board[6]) isItWin=true;
        if(isItWin){
            return gameStatus==O_TURN?O_WIN:X_WIN;
        }
        if(movesCounter==9) {
            return DRAW;
        }
        return gameStatus==O_TURN?X_TURN:O_TURN;
    }

    public int[] getMoves() {
        return moves;
    }
    public int[] getBoard() {
        return board;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public int getMovesCounter() {
        return movesCounter;
    }
}
