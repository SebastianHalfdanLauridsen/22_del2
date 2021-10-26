package test;

import die.Die;
import die.DieManager;

import game.Game;

import org.junit.Test;

import static org.junit.Assert.*;

public class DieManagerTest {

    /**
     * Tests if the throwDie method satisfies
     *   the parameters of the minimum and maximum die value allowed
     */
    @Test
    public void throwDie() {
        int die_amount = 2;
        //create dice
        //and adds them to DieManager's ArrayList
        for (int i = 1; i <= die_amount; i++){
            Die die = new Die(1, i);
            DieManager.addDie(die);
        }

        //roll the dice

        DieManager.throwDies();

        //determine if the face value of the first die is between the minimum allowed and the maximum allowed
        for (int i = 1; i <= die_amount; i++){
            int faceValue = DieManager.getDie(i).getFaceValue();
            assertTrue("Die is out of range, die: " + faceValue,
                    Game.DIE_MIN_VALUE <= faceValue && faceValue <= Game.DIE_MAX_VALUE);
        }

        //sum test------------------------------------------------------------------
        ArrayList<Integer> sumAmount =  new ArrayList<>();
        for(int i = 0; i < 11; i++){
            sumAmount.add(i, 0);
        }

        int amountOfThrows = 10000;
        int counter = 0;
        while(counter < amountOfThrows){
            DieManager.throwDies();
            int sumIndex = DieManager.getSum() - 2;
            sumAmount.set(sumIndex, sumAmount.get(sumIndex) + 1);
            counter++;
        }
        for(int i = 0; i < sumAmount.size(); i++) {
            System.out.println("Sum amount for " + (i + 2) + " = " + sumAmount.get(i));
        }
        /* Used to copy the output for Excel document
        System.out.println("For copying:");
        for(int i = 0; i < sumAmount.size(); i++) {
            System.out.println(sumAmount.get(i));
        }
         */

        counter = 2;
        double combinationTotal = 36;
        ArrayList<Double> theoryPercentage = new ArrayList<>();
        //Fills theoryPercentage with the theoretical posibility of throwing two dice
        for(int i = 1; i <= sumAmount.size(); i++) {
            //If i is bigger than half of the size of the sumAmount, we count down
            if (i > Math.ceil((double)sumAmount.size()/2)){
                theoryPercentage.add(((i - counter)/ combinationTotal) * 100);
                counter += 2;
            } else {
                theoryPercentage.add(i / combinationTotal * 100);
            }
        }
        System.out.println("" + theoryPercentage);


        //Divides the sum amount with amountOfThrows
        ArrayList<Double> sumPercentage = new ArrayList<>();
        for(int i = 0; i < sumAmount.size(); i++){
            sumPercentage.add((sumAmount.get(i)/(double)amountOfThrows) * 100);
        }
        System.out.println(sumPercentage);

        for (int i = 0; i < sumAmount.size(); i++) {
            double difference = Math.abs(theoryPercentage.get(i) - sumPercentage.get(i));
            System.out.printf("\nDifference for sum " + (i + 2) +  ": " + "%.2f", difference);
        }

        //TODO do more specific test Denice ???????????????????? -_-
    }
}