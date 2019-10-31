package me.giose0x56.goosegame.application;

import me.giose0x56.goosegame.domain.GooseGameEventListener;

import java.io.PrintStream;

public class ConsoleGooseGameEventListener implements GooseGameEventListener {

    private final PrintStream printStream;

    public ConsoleGooseGameEventListener(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void playerMoves(String playerName, String fromSpace, String toSpace) {
        printStream.print(String.format(" %s moves from %s to %s", playerName, fromSpace, toSpace));
    }

    @Override
    public void playerMovesAgain(String playerName, String toSpace) {
        printStream.print(String.format(". %s moves again and goes to %s", playerName, toSpace));
    }

    @Override
    public void playerJumps(String playerName, String toSpace) {
        printStream.print(String.format(". %s jumps to %s", playerName, toSpace));
    }

    @Override
    public void playerWins(String playerName) {
        printStream.print(String.format(". %s Wins!!", playerName));
    }

    @Override
    public void playerBounces(String playerName, String backTo) {
        printStream.print(String.format(". %s bounces! %s returns to %s", playerName, playerName, backTo));
    }

    @Override
    public void playerPrank(String playerName, String currentSpace, String backTo) {
        printStream.print(String.format(". On %s there is %s, who returns to %s", currentSpace, playerName, backTo));
    }
}
