package me.giose0x56.goosegame.domain;

import me.giose0x56.goosegame.domain.board.Board;

import java.util.ArrayList;
import java.util.List;

public class GooseGameBuilder {

    private Board board;
    private final List<GooseGameEventListener> gooseGameEventListeners;

    public GooseGameBuilder() {
        this.board = null;
        this.gooseGameEventListeners = new ArrayList<>();
    }

    public GooseGameBuilder withBoard(Board board) {
        this.board = board;
        return this;
    }

    public GooseGameBuilder addListener(GooseGameEventListener gooseGameEventListener) {
        this.gooseGameEventListeners.add(gooseGameEventListener);
        return this;
    }

    public GooseGame build() {
        return new GooseGame(this.board, new GooseGameEventDispatcher(this.gooseGameEventListeners));
    }
}
