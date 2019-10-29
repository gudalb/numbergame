package se.nackademin;

import javax.swing.*;
import java.awt.*;

public class Numbergame extends JFrame {

    static JPanel panel = new JPanel();
    static JButton[] buttons = new JButton[Main.size*Main.size];
    static public int test = 0;


    Numbergame () {
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



        add(panel);

        panel.setLayout(new GridLayout(Main.size,Main.size));

        for (int i = 0; i < buttons.length;i++) {
            buttons[i] = new JButton();
            buttons[i].setText(String.valueOf(Main.numbers[i]));
            buttons[i].setPreferredSize(new Dimension(100,100));
            int finalI = i;
            buttons[i].addActionListener(e -> {

                 if (verifyMove(buttons, buttons[finalI], Main.size)) {

                     for (JButton b : buttons) {
                         if (b.getText().equalsIgnoreCase("0"))
                             b.setText(buttons[finalI].getText());
                     }

                     buttons[finalI].setText("0");
                 }

            });
            panel.add(buttons[i]);
        }

        pack();

    }

    public static boolean verifyMove (JButton[] btns, JButton b, int size) {
        int bLoc = 0;

        // get b index in btns
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

        if (bLoc-1 >= 0) {
            if (btns[bLoc - 1].getText().equalsIgnoreCase("0"))
                return true;
        }

        if (bLoc+1 <= btns.length -1) {
            if (btns[bLoc + 1].getText().equalsIgnoreCase("0"))
                return true;
        }
        return false;
    }

    public static void main () {

        Numbergame g = new Numbergame();
    }
}
