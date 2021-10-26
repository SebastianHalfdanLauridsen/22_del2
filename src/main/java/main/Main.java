package main;

import game.Game;


import gui_fields.GUI_Car;
public class Main {

    public static void main(String[] args) {

        Game game = new Game(2);



        game.playGame();
        System.exit(0);

    }
}