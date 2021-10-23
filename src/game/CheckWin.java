package game;

import main.Main;
import player.InterfacePlayer;
import account.AccountManager;
import player.PlayerManager;

//TODO make le class:
// - Make winCheck method that takes an index to AccountManager's ArrayList childAccounts.
//     This method should call another method if this account's(player's) balance is over 3000
// - Make other method that manipulates Game class to register that a player has won.

public class CheckWin {

    InterfacePlayer winning_player;

    /**
     * Takes an index to an Arraylist childAccounts
     * and returns true if the win condition is less than or equal to the Account's balance
     * @param index in accountManager & playerManager
     * @param accountManager An object of type AccountManager
     * @param playerManager An object of type PlayerManager
     * @return true if the win condition is less than or equal to the Account's balance
     */
    public static boolean winCheck(int index, AccountManager accountManager, PlayerManager playerManager) {
        if (Main.WIN_COND <= accountManager.getAccount(index).getBalance()) {
            playerManager.setWinningPlayer(playerManager.getPlayer(index));
            return true;
        }
        return false;
    }
}
