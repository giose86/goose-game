package me.giose0x56.goosegame.application.command;

import me.giose0x56.goosegame.application.command.exception.UnknownCommandException;
import me.giose0x56.goosegame.domain.GooseGame;

import java.io.PrintStream;

public class GooseGameCommandFactory {

    private final GooseGame gooseGame;
    private final PrintStream out;

    public GooseGameCommandFactory(GooseGame gooseGame, PrintStream out) {

        this.gooseGame = gooseGame;
        this.out = out;
    }

    public GooseGameCommand createFrom(String line) throws UnknownCommandException {

        if (line.matches("^add player\\s(\\w+)$")) {
            return new AddPlayerCommand(gooseGame, out, line.split(" ")[2]);
        } else if (line.matches("^move\\s(\\w+){1}$")) {
            return new MovePlayerCommand(gooseGame, out, line.split(" ")[1]);
        } else if (line.matches("^move\\s(\\w+){1}\\s{1}(\\d){1},(\\d){1}$")) {
            return new MovePlayerWithDicesCommand(gooseGame, out, line.split(" ")[1], line.split(" ")[2]);
        } else if (line.matches("^exit$")) {
            return new ExitGameCommand();
        }
        throw new UnknownCommandException();
    }
}
