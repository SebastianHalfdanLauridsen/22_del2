package test;

import die.Die;
import die.DieManager;
import main.Main;
import org.junit.Test;
import org.w3c.dom.ranges.Range;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DieManagerTest {

    @Test
    public void throwDie() {
        Die die1 = new Die(1, 1);
        Die die2 = new Die(1,2);


        DieManager.addDie(die1);
        DieManager.addDie(die2);

        dieManager.throwDie();

        assertTrue("Die is out of range, die: " + die1.getFaceValue(),
                Main.DIE_MIN_VALUE <= die1.getFaceValue() && die1.getFaceValue() <= Main.DIE_MAX_VALUE);

        assertTrue("Die is out of range, die: " + die2.getFaceValue(),
                Main.DIE_MIN_VALUE <= die2.getFaceValue() && die2.getFaceValue() <= Main.DIE_MAX_VALUE);
    }
}