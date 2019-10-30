package me.giose0x56.goosegame.domain.board.space;

public class StartSpace extends DefaultSpace {

    public StartSpace(int spaceNumber) {
        super(spaceNumber);
    }

    @Override
    public String name() {
        return "Start";
    }
}
