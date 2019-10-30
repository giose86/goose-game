package me.giose0x56.goosegame.domain;

public interface GooseGameEventListener {

    void playerMoves(String playerName, String fromSpace, String toSpace);
    void playerMovesAgain(String playerName, String toSpace);
    void playerJumps(String playerName, String toSpace);
    void playerWins(String playerName);
    void playerBounces(String playerName, String backTo);
}
