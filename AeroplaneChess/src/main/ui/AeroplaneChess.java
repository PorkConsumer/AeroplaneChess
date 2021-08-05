package main.ui;

import main.model.Airplane;
import main.model.Board;
import main.model.Dice;

public class AeroplaneChess {

    public AeroplaneChess() {

    }

    public static void main(String[] args) {
        Board board = new Board();

        Airplane air = new Airplane("yellow",1);
        air.move(50);
        System.out.println(air.getStepsTaken());
        System.out.println(air.getPosition().getColor());
        System.out.println(air.getPosition().getPositionIndex());
        System.out.println(air.getPosition().isSkipBlock());
        System.out.println(air.getStatus());

        System.out.println("=====================");
        Dice dice = new Dice();
        System.out.println(dice.roll());

    }
}
