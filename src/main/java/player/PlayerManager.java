package player;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds all instances of player through InterfacePlayer
 */
public class PlayerManager implements InterfacePlayer {

    private final long id;
    private final String name;
    private InterfacePlayer winning_player;

    private final List<InterfacePlayer> childPlayers;

    public PlayerManager(long id, String name) {
        this.id = id;
        this.name = name;
        this.childPlayers = new ArrayList<>();
        this.winning_player = null;
    }

    @Override
    public void getPlayerDetails() {
        childPlayers.forEach(InterfacePlayer::getPlayerDetails);
    }

    public InterfacePlayer getWinningPlayer() {
        return winning_player;
    }

    public void setWinningPlayer(InterfacePlayer player) {
        winning_player = player;
    }

    /**
     * Takes an object of type InterfacePlayer and adds it to the ArrayList childPlayer
     * @param player An object of type InterfacePlayer
     */
    public void addPlayer(InterfacePlayer player) {
        childPlayers.add(player);
    }

    /**
     * Takes an index to the arrayList childPlayers and returns an object of type InterfacePlayer from childPlayer
     * @param index An index in childPlayers
     * @return An object of type InterfacePlayer from childPlayers
     */
    public InterfacePlayer getPlayer(int index) {
        return childPlayers.get(index-1);
    }

    /**
     * Removes an object of type InterfacePlayer from the ArrayList childPlayer
     * @param index An index of type int in childPlayers
     */
    public void removePlayer(int index) {
        childPlayers.remove(index);
    }

    @Override
    public String toString() {
        return "PlayerManager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
