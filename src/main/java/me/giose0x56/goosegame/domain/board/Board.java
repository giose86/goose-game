package me.giose0x56.goosegame.domain.board;

import me.giose0x56.goosegame.domain.board.space.Space;

import java.util.stream.Stream;

public interface Board {

    int getMaxSpaces();
    void initPlayer(String player);
    Space getCurrentSpaceOf(String player);
    Space movePlayer(String player, int toPosition);
    boolean isWinningSpace(Space space);
    Stream<String> getPlayersInSpace(Space space, String playerNameToExclude);
}
