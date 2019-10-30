package me.giose0x56.goosegame.domain.board.space;

public class TheGooseSpace extends DefaultSpace {

    public TheGooseSpace(int spaceNumber) {
        super(spaceNumber);
    }

    @Override
    public String name() {
        return String.valueOf(spaceNumber) + ", The Goose";
    }
}
