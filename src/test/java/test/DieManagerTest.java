package test;

import die.Die;
import die.DieManager;
import game.Game;
import main.Main;
import org.junit.Test;

import static org.junit.Assert.*;

public class DieManagerTest {

    @Test
    public void throwDie() {
        Die die1 = new Die(1, 1);
        Die die2 = new Die(1,2);


        DieManager.addDie(die1);
        DieManager.addDie(die2);

        DieManager.throwDies();

        assertTrue("Die is out of range, die: " + die1.getFaceValue(),
                Game.DIE_MIN_VALUE <= die1.getFaceValue() && die1.getFaceValue() <= Game.DIE_MAX_VALUE);

        assertTrue("Die is out of range, die: " + die2.getFaceValue(),
                Game.DIE_MIN_VALUE <= die2.getFaceValue() && die2.getFaceValue() <= Game.DIE_MAX_VALUE);

        //TODO make test
        // - Test that the dies are theoretically accurate
    }
}