package player;

/**
 * Handles the information related to the players
 * @author Kevin s195166
 *
 */

public class Player implements InterfacePlayer{
    private final long id;
    private final String name;

    public Player(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void getPlayerDetails() {

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


