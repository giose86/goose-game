package me.giose0x56.goosegame.domain.board;

import me.giose0x56.goosegame.domain.board.space.Space;

public interface Board {

    int getMaxSpaces();
    void initPlayer(String player);
    Space getCurrentSpaceOf(String player);
    Space movePlayer(String player, int toPosition);
    boolean isWinningSpace(Space space);
}
