package me.giose0x56.goosegame.domain.board.space;

public class DefaultSpace implements Space {

    final int spaceNumber;

    public DefaultSpace(int spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    @Override
    public int number() {
        return this.spaceNumber;
    }

    @Override
    public String name() {
        return String.valueOf(spaceNumber);
    }
}
