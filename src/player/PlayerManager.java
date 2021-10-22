package player;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager implements InterfacePlayer {

    private final long id;
    private final String name;

    private List<InterfacePlayer> childPlayers;

    public PlayerManager(long id, String name) {
        this.id = id;
        this.name = name;
        this.childPlayers = new ArrayList<>();
    }

    @Override
    public void getPlayerDetails() {
        childPlayers.forEach(InterfacePlayer::getPlayerDetails);
    }

    @Override
    public String toString() {
        return "PlayerManager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
