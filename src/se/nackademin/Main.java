package se.nackademin;

import javax.swing.*;



public class Main {
    static int size = 4;
    static int[] numbers = new int[size*size];



    public static void main(String[] args) {

        for (int i = 0;i < numbers.length;i++) {
            numbers[i] = i;
        }

        Numbergame.main();


    }
}
