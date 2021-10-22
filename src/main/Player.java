package main;
/**
 * Handles the information related to the players
 * @author Kevin s195166
 *
 */

public class Player {
    private long id;
    private String name;

    public Player(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return id + " " + name;
    }
}


