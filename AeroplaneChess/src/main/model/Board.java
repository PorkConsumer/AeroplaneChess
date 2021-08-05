package main.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Tile> board;

    public Board() {
        board = new ArrayList<>();
        configureBoard();
    }

    public void configureBoard() {
        String color = "red";
        for (int i = 0; i < 52; i++) {
            board.add(i, new Tile(color, i));
            switch (color) {
                case "red":
                    color = "yellow";
                    break;
                case "yellow":
                    color = "blue";
                    break;
                case "blue":
                    color = "green";
                    break;
                case "green":
                    color = "red";
                    break;
            }
        }
        board.get(4).setSkipBlock(true);
        board.get(17).setSkipBlock(true);
        board.get(29).setSkipBlock(true);
        board.get(43).setSkipBlock(true);
    }

    public Tile getTile(int index) {
        if (index > 51) {
            return board.get(index - 50);
        }
        return board.get(index);
    }
}


