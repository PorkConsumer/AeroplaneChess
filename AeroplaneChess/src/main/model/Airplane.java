package model;

// Represents a plane which the player has
public class Airplane {

    private String color;    // color of the plane
    private String status;   // status of the plane
                             // waiting: plane is parked
                             // flying: plane is on hangar or in play on the outer board
                             // porting: plane is in play on the inner board, cannot be killed by other planes
                             // ported: plane has landed in its own base; player receives a point

    private int planeNumber; // plane's number from 1 to 4
    private Tile position;   // the tile that the plane is on
                             // outer board tiles: indexed from 1 to 51
                             // inner board tiles: all indexed 100

    private int stepsTaken;
    private Board board;

    // EFFECTS: initializes a plane
    public Airplane(String color, int planeNumber) {
        this.color = color;
        this.planeNumber = planeNumber;
        status = "waiting";
        stepsTaken = 0;
        board = new Board(); // *Alice: If we initiate Board here then there will have to be a new board created for each plane :0
        position = null;
    }

    // TODO: refactor the remaining 2 cases into shorter helper methods
    // *Alice: maybe keep helper methods in Airplane class and move the Move method (lol) into the GUI/Main class? because it needs a Board to function properly
    // MODIFIES: this
    // EFFECTS: moves the plane on the board by the number that is rolled
    public void move(int numberRolled) {
        switch (status) {
            case "waiting": // if plane is waiting, then begin flight by moving to corresponding initial tile
                beginFlight(numberRolled);
                break;

            case "flying": // if plane is flying, then move on tiles
                Tile nextTile = board.getTile(numberRolled + position.getPositionIndex());

                // check if plane will reach porting
                if (numberRolled + stepsTaken >= 51) {
                    status = "porting";
                    stepsTaken += numberRolled;
                    position = new Tile(color);
                }

                // skipping
                else if (nextTile.isSkipBlock()) {
                    position = board.getTile( numberRolled + 12 + position.getPositionIndex());
                    stepsTaken += (numberRolled + 12);
                }

                // jumping from alike colors
                else if (nextTile.getColor().equals(color)
                && nextTile.getPositionIndex() != 49) {
                    position = board.getTile(numberRolled + position.getPositionIndex() + 4);
                    stepsTaken += (numberRolled + 4);
                    // skipping after jump
                    if (position.isSkipBlock()) {
                        position = board.getTile(numberRolled + 12 + position.getPositionIndex());
                        stepsTaken += (numberRolled + 12);
                    }
                }

                // normal flying
                else {
                    position = board.getTile(numberRolled + position.getPositionIndex());
                    stepsTaken += numberRolled;
                }
                break;

            case "porting": // if plane is porting, check if it's still porting or ported
                if ((numberRolled + stepsTaken) > 56) {
                    stepsTaken = 56 - ((numberRolled + stepsTaken) - 56);
                } else if ((numberRolled + stepsTaken) == 56) {
                    status = "ported";
                }
                break;

        }
    }

    private void beginFlight(int numberRolled) {
        determineStartingElements(numberRolled);
        status = "flying";
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

    public String getColor() {
        return this.color;
    }

    public int getPlaneNumber() {
        return this.planeNumber;
    }
}
