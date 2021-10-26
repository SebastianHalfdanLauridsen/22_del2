package test;

import die.Die;
import die.DieManager;

import game.Game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class DieManagerTest {

    /**
     * Tests if the throwDie method satisfies the parameters of the minimum and maximum die value allowed
     */
    @Test
    public void throwDie() {
        //print operative constants
        System.out.println("Number of possible combinations with " + DIE_AMOUNT + " dies with " + DIE_SIDES + " sides: " + COMBINATION_TOTAL);
        System.out.println("Number of possible sums with " + DIE_AMOUNT + " dies with " + DIE_SIDES + " sides: " + POSSIBLE_SUMS);

        //create dice and adds them to DieManager's ArrayList
        for (int i = 1; i <= DIE_AMOUNT; i++){
            Die die = new Die(1, i);
            DieManager.addDie(die);
        }
        //run tests
        diceTest();
        theoryTest();
    }

    /**
     * Throws the amount of dies and asserts that they are within the given values
     */
    public void diceTest(){
        //roll the dice
        DieManager.throwDies();

        //determine if the face value of the dice is between the minimum allowed and the maximum allowed
        for (int i = 1; i <= DIE_AMOUNT; i++){
            int faceValue = DieManager.getDie(i).getFaceValue();
            assertTrue("Die is out of range, die: " + faceValue,
                    Game.DIE_MIN_VALUE <= faceValue && faceValue <= Game.DIE_MAX_VALUE);
        }
    }

    /**
     * Asserts that the difference in the amount of sums
     * compared to the theoretical possible are within the accepted error
     */
    public void theoryTest(){

        throwDiesForRounds();
        assertSumError();

        //print array results of test
        System.out.println();
        System.out.print("Theoretical percentages: ");
        printArray(theoreticalPercentage);
        System.out.print("Calculated differences:  ");
        printArray(sumPercentage);
        System.out.print("Margin of error:         ");
        printArray(difference);

        //print human readable results of test
        double max_diff = difference.stream().mapToDouble(a->a).max().getAsDouble();
        double avg_diff = difference.stream().mapToDouble(a->a).average().getAsDouble();
        System.out.printf("\nHighest error was: %.4f", max_diff);
        System.out.printf("\nAverage error was: %.4f", avg_diff);
    }

    /**
     * Throws the dice for all rounds and counts each sum into the ArrayList 'sumAmount'
     *  and prints out the sums
     */
    private void throwDiesForRounds(){
        //Throws the dice for all rounds and counts each sum into the ArrayList 'sumAmount'
        for(int i = 0; i < rounds; i++){
            DieManager.throwDies();
            int sumIndex = DieManager.getSum() - DIE_AMOUNT;
            //basically sumAmount += sumIndex
            sumAmount.set(sumIndex, sumAmount.get(sumIndex) + 1);
        }
        //prints out the sums
        for(int i = 0; i < sumAmount.size(); i++) {
            System.out.println("Sum amount for " + (i + DIE_AMOUNT) + " = " + sumAmount.get(i));
        }
    }

    /**
     * Prints the array nicely <3
     * @param array the array to be printed
     */
    private void printArray(ArrayList<Double> array) {
        System.out.print("| ");
        for (Double _double : array) {
            System.out.printf("%.4f", _double);
            System.out.print(" | ");
        }
        System.out.println();
    }

    /**
     * Fills theoryPercentage with the theoretical possibility of throwing two dice
     * 'i' is the amount of possibilites for the sum 2, 3, 4...
     * We use (i - counter) after half the array has been counted through and then we count down
     * Therefore after the next iteration (i++),
     *  'counter' is subtracted and we step 2 down so this iteration is actually i--
     */
    public void theoreticalArrayFiller() {
        int counter = 2;
        for(int i = 1; i <= sumAmount.size(); i++) {
            //If 'i' is bigger than half of the size of the sumAmount, we count down
            if (i > Math.ceil((double)sumAmount.size()/2)){
                theoreticalPercentage.add(((i - counter)/ COMBINATION_TOTAL));
                counter += 2;
            } else { //Else we count up
                theoreticalPercentage.add(i / COMBINATION_TOTAL);
            }
        }
    }

    /**
     * Fills array for sum percentage by dividing each sumAmount with the total amount of rounds
     * Fills difference array by subtracting the theoretical theory with our sum percentage
     * Asserts that the difference is within the accepted error
     */
    public void assertSumError() {
        theoreticalArrayFiller();

        for (int i = 0; i < sumAmount.size(); i++) {
            //Divides the sum amount with the amount of throws and adds the result to sumPercentage
            double sum_percentage = (sumAmount.get(i) / (double) rounds);
            sumPercentage.add(sum_percentage);

            //We use Math.abs to get positive values
            double dif = Math.abs(theoreticalPercentage.get(i) - sumPercentage.get(i));
            difference.add(dif);

            //asserts if the difference between sumPercentage and theoryPercentage is within the acceptedError
            assertTrue("Difference too big for sum: " + (i + 2) +
                    " difference is " + difference.get(i), difference.get(i) < acceptedError);
            System.out.println("sum " + (i + 2) + " passed the test");
        }
    }
}