package me.giose0x56.goosegame.domain;

import me.giose0x56.goosegame.domain.board.Board;
import me.giose0x56.goosegame.domain.board.space.Space;
import me.giose0x56.goosegame.domain.board.space.TheBridgeSpace;
import me.giose0x56.goosegame.domain.board.space.TheGooseSpace;
import me.giose0x56.goosegame.domain.exception.PlayerExistException;
import me.giose0x56.goosegame.domain.exception.PlayerNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GooseGame {

    private final List<String> players;
    private final Board board;
    private final GooseGameEventDispatcher gooseGameEventDispatcher;

    GooseGame(Board board, GooseGameEventDispatcher gooseGameEventDispatcher) {
        this.board = board;
        this.gooseGameEventDispatcher = gooseGameEventDispatcher;
        this.players = new ArrayList<>();
    }

    public void addPlayer(String playerName) throws PlayerExistException {

        if (this.players.contains(playerName)) {
            throw new PlayerExistException();
        } else {
            this.players.add(playerName);
            this.board.initPlayer(playerName);
        }
    }

    public void movePlayer(String playerName, Dices dices) throws PlayerNotFoundException {

        if (!this.players.contains(playerName)) {
            throw new PlayerNotFoundException();
        }

        Space previouslySpace = this.board.getCurrentSpaceOf(playerName);

        int temporaryPosition = previouslySpace.number() + dices.asSum();
        Space nextSpace = this.board.movePlayer(playerName, Math.min(temporaryPosition, board.getMaxSpaces()));
        gooseGameEventDispatcher.dispatchPlayerMoves(playerName, previouslySpace, nextSpace);

        calculateNextPosition(playerName, temporaryPosition, dices.asSum(), nextSpace);

        // check prank
        Space currentSpace = this.board.getCurrentSpaceOf(playerName);
        this.board.getPlayersInSpace(currentSpace, playerName).forEach(playerToPrank -> {
            this.board.movePlayer(playerToPrank, previouslySpace.number());
            this.gooseGameEventDispatcher.dispatchPlayerPrank(playerToPrank, currentSpace, previouslySpace);
        });
    }

    public List<String> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void calculateNextPosition(String playerName, int temporaryPosition, int dicesSum, Space currentSpace) {

        if ((temporaryPosition - board.getMaxSpaces()) > 0) {
            Space bounceSpace = this.board.movePlayer(playerName, board.getMaxSpaces() - (temporaryPosition - board.getMaxSpaces()));
            gooseGameEventDispatcher.dispatchPlayerBounces(playerName, bounceSpace);
        } else if (this.board.isWinningSpace(currentSpace)) {
            gooseGameEventDispatcher.dispatchPlayerWins(playerName);
        } else {

            if (currentSpace instanceof TheBridgeSpace) {
                Space jumpSpace = this.board.movePlayer(playerName, ((TheBridgeSpace) currentSpace).jumpTo());
                gooseGameEventDispatcher.dispatchPlayerJumps(playerName, jumpSpace);
            }

            if (currentSpace instanceof TheGooseSpace) {

                Space nextSpace = this.board.movePlayer(playerName, currentSpace.number() + dicesSum);
                gooseGameEventDispatcher.dispatchPlayerMovesAgain(playerName, nextSpace);
                calculateNextPosition(playerName, currentSpace.number() + dicesSum, dicesSum, nextSpace);
            }
        }
    }
}
