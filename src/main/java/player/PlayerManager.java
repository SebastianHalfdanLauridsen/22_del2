package player;

import gui_fields.*;

import game.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds all instances of GUI_Player, fetches player names and adds them to the GUI
 */
public class PlayerManager {

    private static GUI_Player winning_player;

    private static final List<GUI_Player> childPlayers = new ArrayList<>();

    private static int amount_of_players;
    private static final ArrayList<String> playerNames = new ArrayList<>();

    /**
     * Welcomes the player and lets them input their names
     */
    public static void initiatePlayers() {
        //welcome the player and let them input how many players will play
        Game.getGUI().showMessage("Welcome to the game!");
        amount_of_players = Game.getGUI().getUserInteger("Enter the amount of players (between 2 and 6):", 2, 6);

        String[] ordinal = {"first", "second", "third", "fourth", "fifth", "sixth"};

        //loops through the amount of players who must enter their name, checks to see if they are valid inputs
        //  and adds them to the ArrayList playerNames
        for (int currentPlayer = 1; currentPlayer <= amount_of_players; currentPlayer++) {
            //ask the player to input their name and save to player_name_input
            String player_name_input = Game.getGUI().getUserString(
                    "Enter the " + ordinal[currentPlayer - 1] + " player's name:"
            );

            //if the inpput name is an empty String alert the player and let them input again
            if(inputIsEmptyAlert(player_name_input)) {
                currentPlayer--;
                continue;
            }

            //add the player's input name to playerNames ArrayList
            playerNames.add(player_name_input);

            //if more than 1 player have entered a name and the name is in use
            //remove their input from the ArrayList and let them input their name again
            if (currentPlayer >= 2 && playerNameTaken(player_name_input)) {
                playerNames.remove(currentPlayer - 1);
                currentPlayer--;
            }
        }
    }

    /**
     * Checks if user input is already in the array
     * @param input the name of the user
     * @return true if name is already in array, false if not
     */
    private static boolean playerNameTakenAlert(String input){
        for (int j = 0; j < playerNames.size() - 1; j++) {
            //if this name input is equal to one of the previously recorded names, alert player
            if(input.equals(playerNames.get(j))) {
                Game.getGUI().showMessage("Players cannot share the same name!");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if user input is empty
     * @param input the name of the user
     * @return true if the name is empty, false if not
     */
    private static boolean inputIsEmptyAlert(String input) {
        if ("".equals(input)) {
            //Alert the player that a name cannot be empty
            Game.getGUI().showMessage("You must enter a name!");
            return true;
        }
        return false;
    }

    /**
     *Creates a player with the following attributes and adds them to childPlayers
     * @param name Name of the player
     * @param balance The starting balance for the player
     * @param car The car object of type GUI_Car for the player
     */
    public static void createPlayer(String name, int balance, GUI_Car car) {
        GUI_Player player = new GUI_Player(name, balance, car);
        addPlayer(player);
    }

    /**
     * Adds or decreases the balance of the player based on the input (positive or negative)
     * @param player_index The index of the player that is paying og getting rent
     * @param rent The amount of rent to be given from the field
     * @return true if the balance was edited, false if otherwise
     */
    public static boolean changePlayerBalance(int player_index, int rent) {
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

    /**
     * Takes an index to array for fields and returns its rent
     * @param index An object of type field
     * @return the rent of the field
     */
    public static int getFieldRent(int index) {
        GUI_Brewery field = (GUI_Brewery) Game.getGUI().getFields()[index];
        return Integer.parseInt(field.getRent());
    }

    /**
     * Takes an object of type GUI_Player and adds it to the ArrayList childPlayer
     * @param player An object of type GUI_Player
     */
    private static void addPlayer(GUI_Player player) {
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

    public static ArrayList<String> getPlayerNames() {
        return playerNames;
    }

    public static int getAmountOfPlayers() {
        return amount_of_players;
    }

    public static GUI_Player getWinningPlayer() {
        return winning_player;
    }

    public static void setWinningPlayer(GUI_Player player) {
        winning_player = player;
    }
}
