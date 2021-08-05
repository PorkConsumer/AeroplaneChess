package main.model;

public class Player {

    private String color;
    private int planesInPlay;
    private int score;
    private int planesKilled;

    public Player(String color) {
        this.color = color;
        this.score = 0;
        this.planesKilled = 0;
        this.planesInPlay = 0;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
