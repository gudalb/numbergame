package se.nackademin;

import javax.swing.*;
import java.util.Arrays;
import java.util.Random;


public class Main {
    static int size = 4;
    static int[] numbers = new int[size*size];
    static int[] numbersReference = new int[size*size];



    public static void main(String[] args) {

        for (int i = 0;i < numbers.length;i++) {
            numbers[i] = i;
            numbersReference[i] = 1;
        }

        Random r = new Random();

        for (int i = 0;i < numbers.length;i++) {
            int temp = numbers[i];
            int tempR = r.nextInt(numbers.length);
            numbers[i] = numbers[tempR];
            numbers[tempR] = temp;

        }

        Numbergame.main();


    }
}
