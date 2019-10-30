package me.giose0x56.goosegame.domain.board;

import me.giose0x56.goosegame.domain.board.space.DefaultSpace;
import me.giose0x56.goosegame.domain.board.space.Space;
import me.giose0x56.goosegame.domain.board.space.StartSpace;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardWith63PlacesTest {

    private BoardWith63Places boardWith63Places;

    @Before
    public void setUp() {
        this.boardWith63Places = new BoardWith63Places();
    }

    @Test
    public void givenPlayerName_whenInitPlayer_thenStartPlaceReturns() {

        String playerName = "Pippo";
        this.boardWith63Places.initPlayer(playerName);
        Space currentSpace = this.boardWith63Places.getCurrentSpaceOf(playerName);

        assertThat(currentSpace).isInstanceOf(StartSpace.class);
        assertThat(currentSpace.name()).isEqualTo("Start");
    }

    @Test
    public void givenPlayerName_whenPlayerMove_thenNextPlaceReturns() {

        String playerName = "Pippo";
        this.boardWith63Places.initPlayer(playerName);
        Space nextSpace = this.boardWith63Places.movePlayer(playerName, 8);

        assertThat(nextSpace).isInstanceOf(DefaultSpace.class);
        assertThat(nextSpace.name()).isEqualTo("8");
    }
}
