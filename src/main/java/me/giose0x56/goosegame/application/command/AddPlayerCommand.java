package me.giose0x56.goosegame.application.command;

import me.giose0x56.goosegame.domain.GooseGame;
import me.giose0x56.goosegame.domain.exception.PlayerExistException;

import java.io.PrintStream;

public class AddPlayerCommand implements GooseGameCommand {

    private final GooseGame gooseGame;
    private final PrintStream printStream;
    private final String playerName;

    AddPlayerCommand(GooseGame gooseGame, PrintStream printStream, String playerName) {
        this.gooseGame = gooseGame;
        this.printStream = printStream;
        this.playerName = playerName;
    }

    @Override
    public void execute() {

        try {
            gooseGame.addPlayer(playerName);
            printStream.println("players: " + String.join(", ", gooseGame.getPlayers()));
        } catch (PlayerExistException e) {
            printStream.println(String.format(" %s: already existing player", playerName));
        }
    }

    @Override
    public boolean canExit() {
        return false;
    }
}
