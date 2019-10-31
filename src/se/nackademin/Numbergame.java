package se.nackademin;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Numbergame extends JFrame {

    static JPanel buttonsPanel = new JPanel();
    static JPanel mainPanel = new JPanel();
    static JPanel topPanel = new JPanel();
    static JPanel picPanel = new JPanel();

    static JButton[] buttons = new JButton[Main.size*Main.size];
    static JButton newGame = new JButton("New game");
    static JTextField sizeInput = new JTextField(String.valueOf(Main.size));


    ImageIcon p = new ImageIcon("apa.png");

    Numbergame () {
        setSize(500,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(picPanel, BorderLayout.SOUTH);


        picPanel.setSize(300,100);
        topPanel.add(newGame);
        topPanel.setLayout(new FlowLayout());
        sizeInput.setPreferredSize(new Dimension(50,20));
        topPanel.add(sizeInput);

        newGame.addActionListener(e -> {
            if (setSizeFromInput(sizeInput)) {
                buttonsPanel.removeAll();
                buttons = new JButton[Main.size*Main.size];
                newGame(buttons, buttonsPanel, Main.size);
                randomizeGame(buttons);
                fixButtonVisibility(buttons);
                buttonsPanel.updateUI();
                pack();
            }
            else {
                randomizeGame(buttons);
                fixButtonVisibility(buttons);
            }

        });



        newGame(buttons, buttonsPanel, Main.size);
        randomizeGame(buttons);
        fixButtonVisibility(buttons);



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

    public static boolean setSizeFromInput(JTextField jtf) {
        try {
            int newSize = Integer.parseInt(jtf.getText());
            if (newSize != Main.size) {
                Main.size = newSize;
                return true;
            }
        } catch (Exception e) {
            System.out.println("text from field cant be parsed to int");
        }

        return false;


    }

    public static void fixButtonVisibility (JButton[] btns) {
        for (JButton b:btns) {
            if (b.getText().equalsIgnoreCase("0"))
                b.setVisible(false);
            else
                b.setVisible(true);
        }
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

    public static void newGame (JButton[] btns, JPanel btnsPanel, int size) {
        btnsPanel.setLayout(new GridLayout(size,size));

        for (int i = 0; i < btns.length;i++) {
            btns[i] = new JButton();
            btns[i].setText(String.valueOf(i));
            btns[i].setPreferredSize(new Dimension(65,65));
            int finalI = i;
            btns[i].addActionListener(e -> {

                if (verifyMove(btns, btns[finalI], Main.size)) {

                    for (JButton b : btns) {
                        if (b.getText().equalsIgnoreCase("0")) {
                            b.setText(btns[finalI].getText());
                        }
                    }

                    btns[finalI].setText("0");

                }

                fixButtonVisibility(btns);

                if (winCheck(btns))
                    winPopup();

            });


            buttonsPanel.add(btns[i]);
        }
        fixButtonVisibility(btns);
    }

    public static void randomizeGame (JButton[] btns) {

        Random r = new Random();

        for (int i = 0; i < btns.length;i++) {

            String temp = btns[i].getText();
            int tempR = r.nextInt(btns.length);

            btns[i].setText(btns[tempR].getText());
            btns[tempR].setText(temp);

            }

        fixButtonVisibility(btns);

        }



    public static void main () {

        Numbergame g = new Numbergame();
    }
}
