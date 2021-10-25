package main;

import game.CarManager;
import game.Game;

import java.awt.*;

import gui_fields.GUI_Car;
public class Main {

    public static void main(String[] args) {

        Game game = new Game(2);

        game.createPlayerCars(2);

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