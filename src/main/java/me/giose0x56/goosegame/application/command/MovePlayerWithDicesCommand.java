package me.giose0x56.goosegame.application.command;

import me.giose0x56.goosegame.domain.Dices;
import me.giose0x56.goosegame.domain.GooseGame;
import me.giose0x56.goosegame.domain.exception.PlayerNotFoundException;

import java.io.PrintStream;

public class MovePlayerWithDicesCommand implements GooseGameCommand {

    private final GooseGame gooseGame;
    private final PrintStream printStream;
    private final String playerName;
    private final String dices;

    MovePlayerWithDicesCommand(GooseGame gooseGame, PrintStream printStream, String playerName, String dices) {
        this.gooseGame = gooseGame;
        this.printStream = printStream;
        this.playerName = playerName;
        this.dices = dices;
    }

    @Override
    public void execute() {

        try {

            String[] dicesValue = this.dices.split(",");
            Dices dices = Dices.with(Integer.valueOf(dicesValue[0]), Integer.valueOf(dicesValue[1]));
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
