package main.model;

public class Tile {

    private String color;
    private int positionIndex;
    private boolean isSkipBlock;
    private int portingConstant = 100;

    // constructor for tiles on the outer board
    public Tile(String color, int positionIndex) {
        this.color = color;
        this.positionIndex = positionIndex;
        isSkipBlock = false;
    }

    // constructor for tiles on the inner board
    public Tile(String color) {
        this.color = color;
        this.positionIndex = portingConstant;
        isSkipBlock = false;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public void setPositionIndex(int positionIndex) {
        this.positionIndex = positionIndex;
    }

    public boolean isSkipBlock() {
        return isSkipBlock;
    }

    public void setSkipBlock(boolean skipBlock) {
        isSkipBlock = skipBlock;
    }
}
