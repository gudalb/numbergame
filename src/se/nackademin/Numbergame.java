package se.nackademin;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Flow;

public class Numbergame extends JFrame {

    static JPanel panel = new JPanel();
    static JPanel mainPanel = new JPanel();
    static JPanel topPanel = new JPanel();

    static JButton[] buttons = new JButton[Main.size*Main.size];
    static JButton newGame = new JButton("New game");
    //static JTextField sizeInput = new JTextField("4");



    Numbergame () {
        setSize(500,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        topPanel.add(newGame);
        topPanel.setLayout(new FlowLayout());
        //topPanel.add(sizeInput);

        newGame.addActionListener(e -> {
            randomizeGame(buttons);
        });


        panel.setLayout(new GridLayout(Main.size,Main.size));


        for (int i = 0; i < buttons.length;i++) {
            buttons[i] = new JButton();;
            buttons[i].setText(String.valueOf(Main.numbers[i]));
            buttons[i].setPreferredSize(new Dimension(65,65));
            int finalI = i;
            buttons[i].addActionListener(e -> {

                 if (verifyMove(buttons, buttons[finalI], Main.size)) {

                     for (JButton b : buttons) {
                         if (b.getText().equalsIgnoreCase("0")) {
                             b.setText(buttons[finalI].getText());
                             b.setVisible(true);
                         }
                     }

                     buttons[finalI].setText("0");
                     //todo töm siffror från knappar till numbers array??
                     buttons[finalI].setVisible(false);

                     if (winCheck(buttons))
                         winPopup();

                 }

            });


            panel.add(buttons[i]);
        }

        for (JButton b:buttons) {
            if (b.getText().equalsIgnoreCase("0"))
                b.setVisible(false);
        }

        pack();

    }

    public static boolean verifyMove (JButton[] btns, JButton b, int size) {
        int bLoc = 0;

        for (int i = 0; i < btns.length; i++) {
            if (btns[i].getText().equalsIgnoreCase(b.getText()))
                bLoc = i;
        }

        if (bLoc-size >= 0) {
            if (btns[bLoc - size].getText().equalsIgnoreCase("0"))
                return true;
        }

        if (bLoc+size <= btns.length -1) {
            if (btns[bLoc + size].getText().equalsIgnoreCase("0"))
                return true;
        }

        if (bLoc-1 >= 0 && bLoc % size != 0) {
            if (btns[bLoc - 1].getText().equalsIgnoreCase("0"))
                return true;
        }

        if (bLoc+1 <= btns.length -1 && bLoc % size != size - 1) {
            if (btns[bLoc + 1].getText().equalsIgnoreCase("0"))
                return true;
        }
        return false;
    }

    public static void numbersSwitch () {
        // todo fixa funktion som körs i verifymove som byter plats på intarna i listan

    }

    public static void winPopup () {
        JOptionPane.showMessageDialog(null, "Congrats you won!");
    }

    public static boolean winCheck (JButton[] btns) {
        StringBuilder fromButtons = new StringBuilder();
        StringBuilder winCompare = new StringBuilder();

        for (JButton b:btns) {
            fromButtons.append(b.getText());
        }

        for (int i = 1; i < btns.length; i++)
            winCompare.append(i);

        winCompare.append(0);

        if (fromButtons.compareTo(winCompare) == 0)
            return true;
        return false;
    }

    public static void randomizeGame (JButton[] btns) {

        Random r = new Random();


        for (int i = 0; i < btns.length;i++) {

            String temp = btns[i].getText();
            int tempR = r.nextInt(btns.length);

            btns[i].setText(btns[tempR].getText());
            btns[tempR].setText(temp);

            for (JButton b:btns) {
                if (b.getText().equalsIgnoreCase("0"))
                    b.setVisible(false);
                else
                    b.setVisible(true);
            }

        }

    }

    public static void main () {

        Numbergame g = new Numbergame();
    }
}
