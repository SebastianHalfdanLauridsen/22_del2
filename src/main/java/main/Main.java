package main;

import game.Game;


import gui_fields.GUI_Car;

/**
 * Runs game and exits when the game is finished
 */
public class Main {
    public static void main(String[] args) {

        Game game = new Game(2);

        game.playGame();
        System.exit(0);
    }
}