package main.model;

// Represents a plane which the player has
public class Airplane {

    private String color;   // color of the plane
    private String status;  // status of the plane
    // waiting: plane is parked
    // flying: plane is on hanger or in play on the outer board
    // porting: plane is in play on the inner board, cannot be killed by other planes

    private int planeNumber;// plane's number from 1 to 4
    private Tile position;  // the tile that the plane is on
    // outer board tiles: indexed from 1 to 51
    // inner board tiles: indexed 100

    private int stepsTaken;
    private Board board;

    // EFFECTS: initializes a plane
    public Airplane(String color, int planeNumber) {
        this.color = color;
        this.planeNumber = planeNumber;
        status = "waiting";
        stepsTaken = 0;
        board = new Board();
    }

    public void move(int roll) {
        switch (status) {
            case "waiting":
                determineStartingElements(roll);
                status = "flying";
                break;

            case "flying":
                Tile nextTile = board.getTile(roll + position.getPositionIndex());
                // check if plane will reach porting
                if (roll + stepsTaken >= 51) {
                    status = "porting";
                    stepsTaken += roll;
                    position = new Tile(color);
                }

                // skipping
                else if (nextTile.isSkipBlock()) {
                    position = board.getTile( roll + 12 + position.getPositionIndex());
                    stepsTaken += (roll + 12);
                }

                // jumping from alike colors
                else if (nextTile.getColor().equals(color)
                && nextTile.getPositionIndex() != 49) {
                    position = board.getTile(roll + position.getPositionIndex() + 4);
                    stepsTaken += (roll + 4);
                    // skipping after jump
                    if (position.isSkipBlock()) {
                        position = board.getTile(roll + 12 + position.getPositionIndex());
                        stepsTaken += (roll + 12);
                    }
                }

                // normal flying
                else {
                    position = board.getTile(roll + position.getPositionIndex());
                    stepsTaken += roll;
                }
                break;

            case "porting":
                if ((roll + stepsTaken) > 56) {
                    stepsTaken = 56 - ((roll + stepsTaken) - 56);
                } else if ((roll + stepsTaken) == 56) {
                    status = "ported";
                }

        }
    }

    public void determineStartingElements(int roll) {
        switch (color) {
            case "red":
                position = board.getTile(39 + roll - 1);
                break;
            case "yellow":
                position = board.getTile(roll - 1);
                break;
            case "blue":
                position = board.getTile(13 + roll - 1);
                break;
            case "green":
                position = board.getTile(26 + roll - 1);
                break;
        }
        stepsTaken = roll;
    }


    public int getStepsTaken() {
        return stepsTaken;
    }

    public void setStepsTaken(int stepsTaken) {
        this.stepsTaken = stepsTaken;
    }

    public Tile getPosition() {
        return position;
    }

    public void setPosition(Tile position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
