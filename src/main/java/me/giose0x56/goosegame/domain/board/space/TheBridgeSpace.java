package me.giose0x56.goosegame.domain.board.space;

public class TheBridgeSpace extends DefaultSpace {

    private final int jumpTo;

    public TheBridgeSpace(int spaceNumber, int jumpTo) {
        super(spaceNumber);
        this.jumpTo = jumpTo;
    }

    @Override
    public String name() {
        return "The Bridge";
    }

    public int jumpTo() {
        return jumpTo;
    }
}
