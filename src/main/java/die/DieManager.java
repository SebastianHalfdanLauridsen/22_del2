package die;

import java.util.ArrayList;
import java.util.List;

import game.Game;

/**
 * Holds all instances of Die and throws the dies
 */
public class DieManager {

    private static int sum;

    private static final List<Die> childDies = new ArrayList<>();

    /**
     * Throws all dies in the ArrayList childDies and gives them a random face_value
     *  from the const DIE_MIN_VALUE to the const DIE_MAX_VALUE.
     */
    public static void throwDies() {
        sum = 0;
        for (int index = 1; index < childDies.size()+1 ; index++) {
            getDie(index).setFaceValue( random(Game.DIE_MIN_VALUE, Game.DIE_MAX_VALUE));
            sum += getDie(index).getFaceValue();
        }
    }

    /**
     * Takes a minimum value, a maximum value and returns a random number between these.
     * @param min A long minimum value.
     * @param max A long maximum value.
     * @return A random integer between min and max.
     */
    private static int random(long min, long max) {
        return (int) ((Math.random() * max) + min);
    }

    public static void addDie(Die die) {
        childDies.add(die);
    }

    public static Die getDie(int index) {
        return childDies.get(index-1);
    }

    public void removeDie(int index) {
        childDies.remove(index-1);
    }

    public static int getSum() {
        return sum;
    }

}

