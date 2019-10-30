package me.giose0x56.goosegame.domain.board;

import me.giose0x56.goosegame.domain.board.space.*;

import java.util.HashMap;
import java.util.Map;

public class BoardWith63Places implements Board {

    private static final int MAX_SPACES = 63;

    private final Space[] spaces = new Space[MAX_SPACES + 1];
    private final Map<String, Integer> playerPosition;

    public BoardWith63Places() {

        this.playerPosition = new HashMap<>();
        for (int i = 0; i <= MAX_SPACES; i++) {
            switch (i) {
                case 0:
                    this.spaces[i] = new StartSpace(i);
                    break;
                case 5:
                case 9:
                case 14:
                case 18:
                case 23:
                case 27:
                    this.spaces[i] = new TheGooseSpace(i);
                    break;
                case 6:
                    this.spaces[i] = new TheBridgeSpace(i, 12);
                    break;
                default:
                    this.spaces[i] = new DefaultSpace(i);
            }
        }
    }

    @Override
    public int getMaxSpaces() {
        return MAX_SPACES;
    }

    @Override
    public void initPlayer(String player) {
        this.playerPosition.put(player, 0);
    }

    @Override
    public Space getCurrentSpaceOf(String player) {
        return this.spaces[this.playerPosition.get(player)];
    }

    @Override
    public Space movePlayer(String player, int toPosition) {

        this.playerPosition.put(player, toPosition);
        return this.spaces[toPosition];
    }

    @Override
    public boolean isWinningSpace(Space space) {
        return space.number() == MAX_SPACES;
    }
}
