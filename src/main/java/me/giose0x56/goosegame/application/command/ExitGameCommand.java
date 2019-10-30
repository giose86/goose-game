package me.giose0x56.goosegame.application.command;

public class ExitGameCommand implements GooseGameCommand {

    @Override
    public void execute() {
    }

    @Override
    public boolean canExit() {
        return true;
    }
}
