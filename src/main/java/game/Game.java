package game;

import die.Die;
import die.DieManager;

import player.PlayerManager;

import gui_fields.GUI_Car;
import gui_fields.GUI_Car.Type;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * THE GAME
 */
public class Game {

    private static final int START_BALANCE = 1000;
    private static final int WIN_COND = 3000;
    public static final int DIE_MIN_VALUE = 1;
    public static final int DIE_MAX_VALUE = 6;

    private GUI_Field[] fields;
    private static GUI gui;
    private int round = 0;
    private static int currentField;

    public Game(){
        gui = new GUI();
    }

    //TODO
    // - split into more methods
    /**
     * Creates GUI and calls all the fields, then adds dies to DieManager
     * @param dies The amount of dies to be used in the game
     */
    public Game(int dies) {
        //creates all fields
        createFields();
        gui = new GUI(fields);

        //something something starting of the game
        getGUI().showMessage("Welcome to the game!");
        int amount_of_players = getGUI().getUserInteger("Enter the amount of players (between 2 and 6):",2, 6);

        ArrayList<String> playerNames = new ArrayList<>();
        String[] ordinal = {"first", "second", "third", "fourth", "fifth", "sixth"};
        for(int currentPlayer = 1; currentPlayer <= amount_of_players; currentPlayer++) {
            //ask the player to input their name and save to player_name_input
            String player_name_input = getGUI().getUserString("Enter the " + ordinal[currentPlayer-1] + " player's name:");

            //if the inpput name is an empty String
            if(player_name_input.equals("")) {
                //alert the player that a name cannot be empty an empty String
                getGUI().showMessage("You must enter a name!");

                //let the player input their name again and skip to next iteration
                currentPlayer--;
                continue;
            }

            //add the player's input name to playerNames ArrayList
            playerNames.add(player_name_input);

            //if more that 0 players have entered a name
            if(currentPlayer >= 2) {
                //cycle through all previous player's inputs
                for(int j = 0; j < playerNames.size()-1; j++) {
                    //if this name input is equal to one of the previously recorded names
                    if(player_name_input.equals(playerNames.get(j))) {
                        //Alert the player that sharing a name is not possible
                        getGUI().showMessage("Players cannot share the same name!");
                        //remove their name from the ArrayList
                        playerNames.remove(currentPlayer-1);
                        //let the player input their name again
                        currentPlayer--;
                        //break out of this for loop because at least 1 name in the list was equal to the name input
                        break; //break1
                    }
                }
                //break1
            }
        }
        createPlayerCars(amount_of_players);

        for(int i = 1; i <= amount_of_players; i++) {
            createPlayer(playerNames.get(i-1), CarManager.getCar(i));
        }

        //make create dies function in DieManager
        for(int i = 1; i <= dies; i++) {
            Die die = new Die(0, i);
            DieManager.addDie(die);
        }
    }

    /**
     * Plays the game and handles all rounds
     */
    public void playGame() {
        displayScoreboard();

        while(true) {
            //If WinningPlayer is found announce winner and exit while loop
            if(PlayerManager.getWinningPlayer() != null) {
                System.out.println(
                        "A winner has been found: "
                                + PlayerManager.getWinningPlayer().getName()
                                + " with the balance: "
                                + PlayerManager.getWinningPlayer().getBalance()
                );
                gui.showMessage(
                        "A winner has been found: "
                                + PlayerManager.getWinningPlayer().getName()
                                + " with the balance: "
                                + PlayerManager.getWinningPlayer().getBalance()
                );
                //Exits program after winner is found
                break;
            }
            newRound();
            round++;
        }
    }

    /**
     * Generates random cars for each player in the game
     * @param amount_of_players Runs for loop for amount of players
     */
    public void createPlayerCars(int amount_of_players) {
        String[] patterns = {"FILL", "HORIZONTAL_GRADIANT","DIAGONAL_DUAL_COLOR", "HORIZONTAL_DUAL_COLOR", "HORIZONTAL_LINE", "CHECKERED", "DOTTED", "ZEBRA"};
        String[] types ={"CAR", "TRACTOR", "RACECAR", "UFO"};

        for(int i = 1; i <= amount_of_players; i++){
            int randomPattern = new Random().nextInt(patterns.length);
            int randomType = new Random().nextInt(types.length);

            Color randomColor1 = new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat());
            Color randomColor2 = new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat());

            CarManager.createCar(
                    randomColor1,
                    randomColor2,
                    Type.valueOf(types[randomType]),
                    GUI_Car.Pattern.valueOf(patterns[randomPattern])
            );
        }
    }

    //Maybe move to another location?? Maybe Resource Bundle??
    /**
     * Creates all the fields and stores their attributes for use in the game
     * Utilizes Fieldmannager methods and the GUI_Field Constructor
     */
    private void createFields() {
        fields = new GUI_Field[]{
                //Field 0
                new GUI_Street(
                        "",
                        "Start",
                        "why are you even reading this, this is the start field????",
                        "0",
                        Color.RED,
                        Color.black
                ),
                //Field 2
                new GUI_Street(
                        "Tower",
                        "+250",
                        "You climb the tower for its riches" +
                                " and you get them",
                        "-250",
                        Color.LIGHT_GRAY,
                        Color.black
                ),
                //Field 3
                new GUI_Street(
                        "Crater",
                        "-100",
                        "You come across a crater" +
                                " and fall into it, ouch",
                        "100",
                        Color.LIGHT_GRAY,
                        Color.black
                ),
                //Field 4 Palace gates
                new GUI_Street(
                        "Palace gates",
                        "+100",
                        "You arrive at the Palace gates" +
                                " and is greeted by the Palace maids, " +
                                "they hand you gifts",
                        "-100",
                        Color.LIGHT_GRAY,
                        Color.black
                ),
                //Field 5 Cold desert
                new GUI_Street(
                        "Cold desert",
                        "-20",
                        "The cold of the desert seeps into your bones " +
                                "and you drop some coins",
                        "20",
                        Color.LIGHT_GRAY,
                        Color.black
                ),
                //Field 6 Walled city
                new GUI_Street(
                        "Walled city",
                        "+180",
                        "You climbed over the wall ruins " +
                                "and find gems",
                        "-180",
                        Color.LIGHT_GRAY,
                        Color.black
                ),
                //Field 7 Monastery
                new GUI_Street(
                        "Monastery",
                        "0",
                        "The monks in the Monastery take you in and feed you, " +
                                "you feel well rested for your journey",
                        "0",
                        Color.LIGHT_GRAY,
                        Color.black
                ),
                //Field 8 Black cave
                new GUI_Street(
                        "Black cave",
                        "-70",
                        "You wander into the Black cave" +
                                " and a vampire sneaks his hands into your pockets," +
                                " stealing some coin",
                        "70",
                        Color.LIGHT_GRAY,
                        Color.black
                ),
                //Field 9 Huts in the mountain
                new GUI_Street(
                        "Huts in the mountain",
                        "+60",
                        "You climb the tower for its riches and you get them",
                        "-60",
                        Color.LIGHT_GRAY,
                        Color.black
                ),
                //Field 10 The Werewall
                new GUI_Street(
                        "The Werewall",
                        "-80",
                        "You climb the tower for its riches and you get them - You also get an extra turn",
                        "80",
                        Color.LIGHT_GRAY,
                        Color.black
                ),
                //Field 11 The pit
                new GUI_Street(
                        "The pit",
                        "-50",
                        "You come across a pit and fall into it, oof",
                        "50",
                        Color.LIGHT_GRAY,
                        Color.black
                ),
                //Field 12 Goldmine
                new GUI_Street(
                        "Goldmine",
                        "+650",
                        "You've come across a goldmine, jackpot",
                        "-650",
                        Color.LIGHT_GRAY,
                        Color.black
                )
        };
    }

    /**
     * Throws the dice after current player presses the "OK" button, and displays them
     * Then moves the current player's car to the field the dice have chosen
     * Updates the current player's balance, with the rent of the field
     * Checks if current player's balance is enough to win, if true he wins
     * Check if field is an extra turn field, gives current player an extra turn if true
     */
    public void newRound() {
        //all in this while loop should have its own (or multiple) methods so that the JUnit test can be made.
        for (int index = 1; index <= PlayerManager.getAmountOfPlayers(); index++) {
            //Shows an 'ok' button that when pressed allows the player to take their turn
            gui.showMessage("Roll for " + PlayerManager.getPlayer(index).getName());

            //roll the dice
            DieManager.throwDies();

            //displays dice at given x and y coordinates with their face values
            displayDice(index);

            //moves the current player's car to the field the dice have chosen
            moveCar(index);

            //Updates the current player's balance, with the rent of the field
            updatePlayerBalance(index);

            //Checks if current player's balance is enough to win, if true he wins
            if(playerHasWon(index)) {
                break;
            }

            //If the current field is The Werewall (field 10)
            // then the next index in the for loop will be the current index
            if(fieldIsExtraTurn()) {
                index -= 1;
            }
        }
    }

    /**
     * Updates the current player's balance, with the rent of the current field
     * @param player_index players balance to be updated
     */
    private void updatePlayerBalance(int player_index){
        int fieldRent = PlayerManager.getStreetFieldRent(currentField);
        PlayerManager.changePlayerBalance(player_index,fieldRent);
    }

    /**
     * Checks if the current field is The Werewall (extra turn)
     * @return True if it is The Werewall, false otherwise
     */
    private boolean fieldIsExtraTurn() {
        return gui.getFields()[currentField] == gui.getFields()[10 - 1];
    }
    /**
     * Checks if current player's balance is enough to win
     * @return Returns true if player has won, false otherwise
     */
    private boolean playerHasWon(int player_index) {
        if(PlayerManager.getPlayer(player_index).getBalance() >= WIN_COND) {
            PlayerManager.setWinningPlayer(PlayerManager.getPlayer(player_index));
            return true;
        }
        return false;
    }

    /**
     * Adds a player with the given parameters using PlayerManager
     * @param player_name
     * @param player_car
     */
    public void createPlayer(String player_name, GUI_Car player_car) {
        PlayerManager.createPlayer(player_name,START_BALANCE,player_car);
    }

    /**
     * Displays the scoreboard and also the players on start
     */
    private void displayScoreboard() {
        //Displays player on the starting field and scoreboard
        for (int i = 1; i <= PlayerManager.getAmountOfPlayers(); i++) {
            gui.getFields()[0].setCar(PlayerManager.getPlayer(i), true);
            gui.addPlayer(PlayerManager.getPlayer(i));
        }
    }

    /**
     * Displays the dice at the given x and y coordinates
     * @param position uses the player index to print the dies at different coordinates
     */
    private void displayDice(int position) {
        gui.setDice(
                DieManager.getDie(1).getFaceValue(),
                position,
                2,
                DieManager.getDie(2).getFaceValue(),
                position + 1,
                2
        );
    }

    /**
     * Removes the specified player's previous car from every field,
     *   and draws it on the current one
     * @param player_index The index of a player that is getting their car moved
     */
    private void moveCar(int player_index) {
        //set the field the players move to
        currentField = ( DieManager.getSum() - 1 );

        //Deletes the current player's car from all fields
        for(GUI_Field f : gui.getFields()) f.setCar(PlayerManager.getPlayer(player_index), false);
        //Displays the current player's car on the current field
        gui.getFields()[currentField].setCar(PlayerManager.getPlayer(player_index), true);
    }

    public static GUI getGUI() {
        return gui;
    }

    public static int getCurrentField() {
        return currentField;
    }
}
