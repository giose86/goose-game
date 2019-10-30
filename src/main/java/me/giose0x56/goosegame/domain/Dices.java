package me.giose0x56.goosegame.domain;

import java.util.Random;

public class Dices {

    private final int dice1;
    private final int dice2;

    private Dices(int dice1, int dice2) {
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    public static Dices with(int dice1, int dice2) {
        return new Dices(dice1, dice2);
    }

    public static Dices throwsDices() {

        int dice1 = 1 + new Random().nextInt(6);
        int dice2 = 1 + new Random().nextInt(6);
        return new Dices(dice1, dice2);
    }

    public int asSum() {
        return this.dice1 + this.dice2;
    }

    public int firstDice() {
        return this.dice1;
    }

    public int secondDice() {
        return this.dice2;
    }
}
