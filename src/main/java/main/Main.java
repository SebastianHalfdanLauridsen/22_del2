package main;

import game.CarManager;
import game.Game;

import java.awt.*;

import gui_fields.GUI_Car;
public class Main {

    public static final long START_BALANCE = 1000;
    public static final long WIN_COND = 3000;
    public static final long DIE_MIN_VALUE = 1;
    public static final long DIE_MAX_VALUE = 6;

    public static void main(String[] args) {

        Game game = new Game(2);

        CarManager.createCar(
                Color.GREEN,
                Color.YELLOW,
                GUI_Car.Type.TRACTOR,
                GUI_Car.Pattern.HORIZONTAL_LINE
        );

        CarManager.createCar(
                Color.MAGENTA,
                Color.CYAN,
                GUI_Car.Type.UFO,
                GUI_Car.Pattern.HORIZONTAL_LINE
        );

        //Create players and add them to PlayerManager
        game.createPlayers(
                "Slange",
                "Kevse",
                CarManager.getCar(1),
                CarManager.getCar(2)
        );

        game.playGame();
        System.exit(0);

    }
}