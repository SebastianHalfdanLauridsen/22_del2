package test;

import game.CarManager;
import game.Game;
import gui_fields.GUI_Car;
import org.junit.Test;
import player.PlayerManager;

import java.awt.*;

import static org.junit.Assert.*;

public class PlayerBalanceTest {

    /**
     * Tests if the players' balance is manipulated correctly
     */
    @Test
    public void changePlayerBalance() {
        Game game = new Game();

        CarManager.createCar(
                Color.GREEN,
                Color.YELLOW,
                GUI_Car.Type.TRACTOR,
                GUI_Car.Pattern.HORIZONTAL_LINE
        );
        CarManager.createCar(
                Color.MAGENTA,
                Color.CYAN,
                GUI_Car.Type.UFO,
                GUI_Car.Pattern.HORIZONTAL_LINE
        );
        game.createPlayer(
                "Slange",
                CarManager.getCar(1)
        );
        game.createPlayer(
                "Kevse",
                CarManager.getCar(2)
        );

        //Test to see if a change has not been made to the player balance
        if (!PlayerManager.changePlayerBalance(1, 0)){
            assertEquals(1000,PlayerManager.getPlayer(1).getBalance());
        }
        //Test to see if the player's balance cannot go below 0
        if (PlayerManager.changePlayerBalance(2, 1000)){
            assertEquals(0,PlayerManager.getPlayer(2).getBalance());
        }
        //Test to see if player balance is added
        if (PlayerManager.changePlayerBalance(2, -1000)){
            assertEquals(1000,PlayerManager.getPlayer(2).getBalance());
        }

        //Test so see if logic is working
        PlayerManager.changePlayerBalance(1, 1001);
        PlayerManager.changePlayerBalance(2, 999);

        assertEquals(0,PlayerManager.getPlayer(1).getBalance());
        assertEquals(1,PlayerManager.getPlayer(2).getBalance());

        //Test with MAX and MIN VALUE
        PlayerManager.changePlayerBalance(1, -Integer.MAX_VALUE);
        PlayerManager.changePlayerBalance(2, Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE,PlayerManager.getPlayer(1).getBalance());
        assertEquals(0,PlayerManager.getPlayer(2).getBalance());
        System.out.println("All PlayerBalanceTests were successful and passed");
    }
}