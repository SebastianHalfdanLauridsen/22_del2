package player;

import gui_fields.*;
import gui_main.GUI;

import game.Game;

import java.util.ArrayList;
import java.util.List;

import gui_fields.GUI_Street;
/**
 * Holds all instances of GUI_Player
 */
public class PlayerManager {

    private static GUI_Player winning_player;

    private static final List<GUI_Player> childPlayers = new ArrayList<>();



    public static GUI_Player getWinningPlayer() {
        return winning_player;
    }

    public static void setWinningPlayer(GUI_Player player) {
        winning_player = player;
    }

    /**
     *
     * @param name
     * @param balance
     * @param car
     */
    public static void createPlayer(String name, int balance, GUI_Car car) {
        GUI_Player player = new GUI_Player(name, balance, car);
        addPlayer(player);
    }

    /**
     * Adds or decreases the balance of the player based on the input (positive or negative)
     * @param player_index The index of the player that is paying rent
     * @return true if the balance was edited, false if otherwise
     */
    public static boolean changePlayerBalance(int player_index, int rent) {
        //parse the field that has been landed on to GUI_Street

        //variables to help make code nice B)
        int currBalance = PlayerManager.getPlayer(player_index).getBalance();


        //Check to see if balance is unchanged
        //if currBalance is 0 AND rent is more than 0 OR rent is 0, return false
        if((currBalance == 0 && rent > 0) || rent == 0){
            return false;
        }
        //Check to see if balance is going to be minus, if true set balance to 0
        if((currBalance - rent) < 0){
            PlayerManager.getPlayer(player_index).setBalance(0);
            return true;
        }
        //add or remove coins from the current player's balance
        PlayerManager.getPlayer(player_index).setBalance(currBalance - rent);
        return true;
    }

    public static int getStreetFieldRent(int index) {
        GUI_Street street = (GUI_Street) Game.getGUI().getFields()[index];
        return Integer.parseInt(street.getRent());
    }

    /**
     * Takes an object of type GUI_Player and adds it to the ArrayList childPlayer
     * @param player An object of type GUI_Player
     */
    public static void addPlayer(GUI_Player player) {
        childPlayers.add(player);
    }

    /**
     * Takes an index to the arrayList childPlayers and returns an object of type GUI_Player from childPlayer
     * @param index An index in childPlayers
     * @return An object of type GUI_Player from childPlayers
     */
    public static GUI_Player getPlayer(int index) {
        return childPlayers.get(index-1);
    }

    /**
     * Removes an object of type GUI_Player from the ArrayList childPlayer
     * @param index An index of type int in childPlayers
     */
    public void removePlayer(int index) {
        childPlayers.remove(index);
    }

    public static long getAmountOfPlayers() {
        return childPlayers.size();
    }

}
