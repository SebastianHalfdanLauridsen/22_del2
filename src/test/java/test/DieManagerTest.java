package test;

import die.Die;
import die.DieManager;

import game.Game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class DieManagerTest {

    //variables
    final int rounds = 10000;
    final double acceptedError = 0.01;

    //constants
    final int DIE_AMOUNT = 2;
    final int DIE_SIDES = 6;
    final double COMBINATION_TOTAL = Math.pow(DIE_SIDES, DIE_AMOUNT);
    final int POSSIBLE_SUMS = DIE_AMOUNT * DIE_SIDES -(DIE_AMOUNT -1);

    //fills sumAmount with 0
    ArrayList<Integer> sumAmount = new ArrayList<>(Collections.nCopies(POSSIBLE_SUMS, 0));

    ArrayList<Double> theoreticalPercentage = new ArrayList<>();
    ArrayList<Double> sumPercentage = new ArrayList<>();
    ArrayList<Double> difference = new ArrayList<>();

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
     * Fills theoryPercentage with the theoretical possibilities of throwing two dice
     */
    public void theoreticalArrayFiller() {
        // 'i' is the amount of possible sums
        // We use ((sumAmount.size()+1)-i) after half the array has been counted through to count down
        // Therefore after the next iteration (i++),
        // So for 2 dice [sumAmount.size()=11]
        // we first count up to 6
        // and after 6, 7 is bigger than half of the ArrayList [(i>Math.ceil(sumAmount.size()/2)=TRUE]
        // then we count down with (sumAmount.size()+1)-i
        // ex. sumAmount=11, i=7: (11+1)-7=5
        // and the next ex. sumAmount=11, i=8: (11+1)-8 = 4
        // etc.
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