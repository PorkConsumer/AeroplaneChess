package main.model;

public class Dice {

    private int diceNumber;

    public Dice() {

    }

    public int roll() {
        diceNumber = (int)(Math.random() * 6 + 1);
        return diceNumber;
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    public void setDiceNumber(int diceNumber) {
        this.diceNumber = diceNumber;
    }
}
