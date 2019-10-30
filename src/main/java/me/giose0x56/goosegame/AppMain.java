package me.giose0x56.goosegame;


import me.giose0x56.goosegame.application.ConsoleGooseGameEventListener;
import me.giose0x56.goosegame.application.command.GooseGameCommand;
import me.giose0x56.goosegame.application.command.GooseGameCommandFactory;
import me.giose0x56.goosegame.application.command.exception.UnknownCommandException;
import me.giose0x56.goosegame.domain.GooseGame;
import me.giose0x56.goosegame.domain.GooseGameBuilder;
import me.giose0x56.goosegame.domain.board.BoardWith63Places;

import java.util.Scanner;

public class AppMain {

    public static void main(String[] args) {

        GooseGame gooseGame = new GooseGameBuilder()
                .withBoard(new BoardWith63Places())
                .addListener(new ConsoleGooseGameEventListener(System.out))
                .build();

        GooseGameCommandFactory gooseGameCommandFactory = new GooseGameCommandFactory(gooseGame, System.out);

        System.out.println("Welcome to Goose Game!");
        System.out.println("Available commands:");
        System.out.println("-> add player <player name>");
        System.out.println("-> move <player name> <dice1,dice2>");
        System.out.println("-> move <player name>");
        System.out.println("-> exit");

        try (Scanner scanner = new Scanner(System.in)) {

            boolean canExit = false;
            do {
                try {

                    String line = scanner.nextLine();
                    GooseGameCommand command = gooseGameCommandFactory.createFrom(line);
                    command.execute();
                    canExit = command.canExit();

                } catch (UnknownCommandException e) {
                    System.out.println("unknown command");
                }

            } while (!canExit);
        }
        System.out.println("Goose Game end.");
    }
}
