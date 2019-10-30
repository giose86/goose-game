package me.giose0x56.goosegame.domain;

import me.giose0x56.goosegame.domain.board.space.Space;

import java.util.List;

class GooseGameEventDispatcher {

    private final List<GooseGameEventListener> gooseGameEventListeners;

    GooseGameEventDispatcher(List<GooseGameEventListener> gooseGameEventListeners) {
        this.gooseGameEventListeners = gooseGameEventListeners;
    }

    public void dispatchPlayerMoves(String playerName, Space fromSpace, Space toSpace) {
        this.gooseGameEventListeners.forEach(gooseGameEventListener -> gooseGameEventListener.playerMoves(playerName,
                fromSpace.name(), toSpace.name()));
    }

    public void dispatchPlayerMovesAgain(String playerName, Space toSpace) {
        this.gooseGameEventListeners.forEach(gooseGameEventListener -> gooseGameEventListener.playerMovesAgain(playerName, toSpace.name()));
    }

    public void dispatchPlayerWins(String playerName) {
        this.gooseGameEventListeners.forEach(gooseGameEventListener -> gooseGameEventListener.playerWins(playerName));
    }

    public void dispatchPlayerJumps(String playerName, Space toSpace) {
        this.gooseGameEventListeners.forEach(gooseGameEventListener -> gooseGameEventListener.playerJumps(playerName, toSpace.name()));
    }

    public void dispatchPlayerBounces(String playerName, Space backTo) {
        this.gooseGameEventListeners.forEach(gooseGameEventListener -> gooseGameEventListener.playerBounces(playerName, backTo.name()));
    }
}
