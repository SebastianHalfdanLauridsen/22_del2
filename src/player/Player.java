package player;

import main.Main;

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

        this.account = new Account(id, Main.START_BALANCE);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}


