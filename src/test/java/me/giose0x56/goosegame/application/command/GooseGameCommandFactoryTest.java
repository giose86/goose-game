package me.giose0x56.goosegame.application.command;

import me.giose0x56.goosegame.application.command.exception.UnknownCommandException;
import me.giose0x56.goosegame.domain.GooseGame;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class GooseGameCommandFactoryTest {

    private GooseGameCommandFactory gooseGameCommandFactory;

    @Before
    public void setUp() {

        GooseGame gooseGame = mock(GooseGame.class);
        PrintStream out = mock(PrintStream.class);

        this.gooseGameCommandFactory = new GooseGameCommandFactory(gooseGame, out);
    }

    @Test
    public void givenAddPlayerCommandString_whenParseString_thenAddPlayerCommandInstanceReturns()
            throws UnknownCommandException {

        String addPlayer = "add player Pippo";
        GooseGameCommand gooseGameCommand =
                this.gooseGameCommandFactory.createFrom(addPlayer);

        assertThat(gooseGameCommand).isInstanceOf(AddPlayerCommand.class);
    }

    @Test
    public void givenMovePlayerCommandString_whenParseString_thenMovePlayerCommandInstanceReturns()
            throws UnknownCommandException {

        String moveCommand = "move Pippo";
        GooseGameCommand gooseGameCommand =
                this.gooseGameCommandFactory.createFrom(moveCommand);

        assertThat(gooseGameCommand).isInstanceOf(MovePlayerCommand.class);
    }

    @Test
    public void givenMovePlayerWithDiceCommandString_whenParseString_thenMovePlayerWithDicesCommandInstanceReturns()
            throws UnknownCommandException {

        String moveCommandWithDices = "move Pippo 1,2";
        GooseGameCommand gooseGameCommand =
                this.gooseGameCommandFactory.createFrom(moveCommandWithDices);

        assertThat(gooseGameCommand).isInstanceOf(MovePlayerWithDicesCommand.class);
    }

    @Test
    public void givenExitCommandString_whenParseString_thenExitCommandInstanceReturns()
            throws UnknownCommandException {

        String exitCommand = "exit";
        GooseGameCommand gooseGameCommand =
                this.gooseGameCommandFactory.createFrom(exitCommand);

        assertThat(gooseGameCommand).isInstanceOf(ExitGameCommand.class);
    }

    @Test
    public void givenCommandNotWeelFormedString_whenParseString_thenUnknownCommandExceptionThrows() {

        String exitCommand = "move player Pippo";

        assertThatThrownBy(() -> this.gooseGameCommandFactory.createFrom(exitCommand))
                .isInstanceOf(UnknownCommandException.class);
    }
}
