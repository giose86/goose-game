package me.giose0x56.goosegame.domain;

import me.giose0x56.goosegame.domain.board.Board;
import me.giose0x56.goosegame.domain.board.BoardWith63Places;
import me.giose0x56.goosegame.domain.board.space.*;
import me.giose0x56.goosegame.domain.exception.PlayerExistException;
import me.giose0x56.goosegame.domain.exception.PlayerNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class GooseGameTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    private GooseGame gooseGame;
    private Board board;
    private GooseGameEventDispatcher gooseGameEventDispatcher;

    @Before
    public void setUp() {

        this.board = new BoardWith63Places();
        this.gooseGameEventDispatcher = mock(GooseGameEventDispatcher.class);
        this.gooseGame = new GooseGame(this.board, gooseGameEventDispatcher);
    }

    @Test
    public void whenAddOnePlayer_thenPlayersCountIsOne() throws PlayerExistException {

        this.gooseGame.addPlayer("Pippo");
        assertThat(1).isEqualTo(gooseGame.getPlayers().size());
    }

    @Test
    public void whenAddTwoPlayer_thenPlayerCountIsTwo() throws PlayerExistException {

        this.gooseGame.addPlayer("Pippo");
        this.gooseGame.addPlayer("Pluto");

        assertThat(2).isEqualTo(gooseGame.getPlayers().size());
    }

    @Test
    public void whenAddAPlayerThatExist_thenPlayeExistExceptionIsThrown() throws PlayerExistException {

        this.exceptionRule.expect(PlayerExistException.class);

        this.gooseGame.addPlayer("pippo");
        this.gooseGame.addPlayer("pippo");
    }

    @Test
    public void givenPlayerInStart_whenPlayerMove_thenPlayerIsInTheNewSpace()
            throws PlayerExistException, PlayerNotFoundException {

        String playerName = "Pippo";

        Dices dices = Dices.with(3, 1);
        this.gooseGame.addPlayer(playerName);
        this.gooseGame.movePlayer(playerName, dices);
        Space currentSpace = this.board.getCurrentSpaceOf(playerName);

        assertThat(currentSpace).isInstanceOf(DefaultSpace.class);
        assertThat(currentSpace.name()).isEqualTo("4");
    }

    @Test
    public void givenPlayerInPlace60_whenPlayerMovesWithExactDices_thenPlayerWins()
            throws PlayerExistException, PlayerNotFoundException {

        String playerName = "Pippo";

        this.gooseGame.addPlayer(playerName);
        this.board.movePlayer(playerName, 60);

        Dices dices = Dices.with(2, 1);
        this.gooseGame.movePlayer(playerName, dices);
        Space currentSpace = this.board.getCurrentSpaceOf(playerName);

        assertThat(currentSpace).isInstanceOf(DefaultSpace.class);
        assertThat(currentSpace.name()).isEqualTo("63");
        verify(gooseGameEventDispatcher, times(1)).
                dispatchPlayerMoves(anyString(), any(Space.class), any(Space.class));
        verify(gooseGameEventDispatcher, times(1)).dispatchPlayerWins(playerName);
    }

    @Test
    public void givenPlayerInPlace60_whenPlayerMovesWithNoExactDices_thenPlayerBounces()
            throws PlayerExistException, PlayerNotFoundException {

        String playerName = "Pippo";

        this.gooseGame.addPlayer(playerName);
        this.board.movePlayer(playerName, 60);

        Dices dices = Dices.with(2, 3);
        this.gooseGame.movePlayer(playerName, dices);
        Space currentSpace = this.board.getCurrentSpaceOf(playerName);

        assertThat(currentSpace).isInstanceOf(DefaultSpace.class);
        assertThat(currentSpace.name()).isEqualTo("61");

        verify(gooseGameEventDispatcher, times(1)).
                dispatchPlayerMoves(anyString(), any(Space.class), any(Space.class));
        verify(gooseGameEventDispatcher, times(1)).
                dispatchPlayerBounces(playerName, currentSpace);
    }

    @Test
    public void givenPlayerInStart_whenPlayerMovesToTheBridge_thenPlayerJumpToPlace12()
            throws PlayerExistException, PlayerNotFoundException {

        String playerName = "Pippo";

        this.gooseGame.addPlayer(playerName);

        Dices dices = Dices.with(3, 3);
        this.gooseGame.movePlayer(playerName, dices);
        Space currentSpace = this.board.getCurrentSpaceOf(playerName);

        assertThat(currentSpace).isInstanceOf(DefaultSpace.class);
        assertThat(currentSpace.name()).isEqualTo("12");

        verify(gooseGameEventDispatcher, times(1)).
                dispatchPlayerMoves(anyString(), any(StartSpace.class), any(TheBridgeSpace.class));
        verify(gooseGameEventDispatcher, times(1)).
                dispatchPlayerJumps(playerName, currentSpace);
    }

    @Test
    public void givenPlayerInStart_whenPlayerMovesToTheGoose_thenPlayerMovesAgain()
            throws PlayerExistException, PlayerNotFoundException {

        String playerName = "Pippo";

        this.gooseGame.addPlayer(playerName);

        Dices dices = Dices.with(3, 2);
        this.gooseGame.movePlayer(playerName, dices);
        Space currentSpace = this.board.getCurrentSpaceOf(playerName);

        assertThat(currentSpace).isInstanceOf(DefaultSpace.class);
        assertThat(currentSpace.name()).isEqualTo("10");

        verify(gooseGameEventDispatcher, times(1)).
                dispatchPlayerMoves(anyString(), any(StartSpace.class), any(TheGooseSpace.class));
        verify(gooseGameEventDispatcher, times(1)).
                dispatchPlayerMovesAgain(playerName, currentSpace);
    }

    @Test
    public void givenPlayerInStart_whenPlayerMovesToTheGooseTwoTime_thenPlayerMovesAgainTwoTime()
            throws PlayerExistException, PlayerNotFoundException {

        String playerName = "Pippo";

        this.gooseGame.addPlayer(playerName);
        this.board.movePlayer(playerName, 10);

        Dices dices = Dices.with(2, 2);
        this.gooseGame.movePlayer(playerName, dices);

        Space currentSpace = this.board.getCurrentSpaceOf(playerName);

        assertThat(currentSpace).isInstanceOf(DefaultSpace.class);
        assertThat(currentSpace.name()).isEqualTo("22");
        verify(gooseGameEventDispatcher, times(1)).
                dispatchPlayerMovesAgain(anyString(), any(TheGooseSpace.class));
        verify(gooseGameEventDispatcher, times(1)).
                dispatchPlayerMovesAgain(anyString(), any(TheGooseSpace.class));
        verify(gooseGameEventDispatcher, times(1)).
                dispatchPlayerMovesAgain(playerName, currentSpace);
    }

    @Test
    public void givenTwoPlayer_whenPlayerOneMovesInTheSpaceOccupiedByAnotherPlayerTwo_thenPlayerTwoSentToPreviousPlayerOnePosition()
            throws PlayerExistException, PlayerNotFoundException {

        String player1 = "Pippo";
        String player2 = "Pluto";

        this.gooseGame.addPlayer(player1);
        this.gooseGame.addPlayer(player2);
        this.board.movePlayer(player2, 10);

        Dices dices = Dices.with(5, 5);
        this.gooseGame.movePlayer(player1, dices);

        Space currentPlayer1Space = this.board.getCurrentSpaceOf(player1);
        Space currentPlayer2Space = this.board.getCurrentSpaceOf(player2);

        assertThat(currentPlayer1Space).isInstanceOf(DefaultSpace.class);
        assertThat(currentPlayer1Space.name()).isEqualTo("10");
        assertThat(currentPlayer2Space).isInstanceOf(StartSpace.class);
        assertThat(currentPlayer2Space.name()).isEqualTo("Start");
        verify(gooseGameEventDispatcher, times(1)).
                dispatchPlayerMoves(anyString(), any(Space.class), any(Space.class));
        verify(gooseGameEventDispatcher, times(1)).
                dispatchPlayerPrank(anyString(), any(Space.class), any(Space.class));
    }
}
