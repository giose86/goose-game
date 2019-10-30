package me.giose0x56.goosegame.application.command;

import me.giose0x56.goosegame.domain.Dices;
import me.giose0x56.goosegame.domain.GooseGame;
import me.giose0x56.goosegame.domain.exception.PlayerNotFoundException;

import java.io.PrintStream;

public class MovePlayerCommand implements GooseGameCommand {

    private final GooseGame gooseGame;
    private final PrintStream printStream;
    private final String playerName;

    MovePlayerCommand(GooseGame gooseGame, PrintStream printStream, String playerName) {
        this.gooseGame = gooseGame;
        this.printStream = printStream;
        this.playerName = playerName;
    }

    @Override
    public void execute() {

        try {

            Dices dices = Dices.throwsDices();
            printStream.print(String.format("%s rolls %d, %d.", playerName, dices.firstDice(), dices.secondDice()));

            gooseGame.movePlayer(playerName, dices);
            printStream.println();

        } catch (PlayerNotFoundException e) {
            printStream.println(" Player not found");
        }
    }

    @Override
    public boolean canExit() {
        return false;
    }
}
