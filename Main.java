package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {

        public static void initiate() {
            String openingMessage = "You are about to take the Jordan Standard Test. " +
                    "Please enter the Jordan Standard below, including the correct capitalization " +
                    "of each word. The passing score is 3 wrong or less. Good luck!";

            JFrame frame = new JFrame("Jordan Standard Test");

            JPanel panel = new JPanel ();
            panel.setLayout(new FlowLayout());

            //creates the opening message
            JTextArea label = new JTextArea(openingMessage, 3, 40);
            label.setLineWrap(true);
            label.setWrapStyleWord(true);
            label.setEditable(false);

            JButton button = new JButton();
            button.setText("Start Test");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    standardTest();
                    frame.setVisible(false);
                }
            });

            panel.add(label);
            panel.add(button);

            frame.add(panel);
            frame.setSize(500, 700);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);


        }


        public static void standardTest() {
            //creates the GUI
            JFrame frame = new JFrame("Jordan Standard Test");

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());

            JLabel label = new JLabel("Enter Jordan Standard below");

            JTextArea area = new JTextArea(5, 30);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);

            JButton enter = new JButton();
            enter.setText("Enter");
            enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = getAnswer(area);
                    checkStandard(input);
                    area.setEditable(false);
                }
            });

            panel.add(label);
            panel.add(area);
            panel.add(enter);

            frame.add(panel);
            frame.setSize(500, 700);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }

        public static String getAnswer(JTextArea j) {
            String answer = j.getText();
            return answer;
        }

        private static void checkStandard(String p) {
            String pledges = p.replaceAll("\\s+", " ");
            int numWrong = 0;

            String JS = "The confidence of the Founders of Sigma Chi was based upon a belief that the principles which they" +
                    " professed and the ideal of the Fraternity which they sought were but imperfectly realized in the" +
                    " organizations by which they were surrounded. The standard with which the Fraternity started was" +
                    " declared by Isaac M. Jordan to be that of admitting no man to membership in Sigma Chi who is not" +
                    " believed to be: A Man of Good Character. A Student of Fair Ability. With Ambitious Purposes" +
                    ". A Congenial Disposition. Possessed of Good Morals. Having a High Sense of Honor and A Deep Sense" +
                    " of Personal Responsibility.";

            ArrayList<String> JordanStd = new ArrayList<String>();

            //following two for loops add each individual word
            //to an ArrayList
            for (String word : JS.split(" ")) {
                String y = word.trim();
                JordanStd.add(y);
            }

            ArrayList<String> userStr = new ArrayList<String>();

            for (String word : pledges.split(" ")) {
                String n = word.trim();
                userStr.add(n);
            }

            //checks the similarities between the two individual words of each string
            //if they aren't the same, it adds one to the total missed
            for (int i = 0; i < userStr.size() && i < JordanStd.size(); i++) {
                String actual = JordanStd.get(i);
                String samp = userStr.get(i);
                String x = samp.replaceAll("\\s+", "");
                if (x.compareTo(actual) != 0) {
                    numWrong++;
                }
            }

            //accounts for differences in size between the user string and correct standard
            if (userStr.size() != JordanStd.size()) {
                numWrong += Math.abs((JordanStd.size() - userStr.size()));
            }

            if (numWrong <= 3) {
                JOptionPane.showMessageDialog(new JFrame(), "You passed with " + numWrong +
                        " wrong. Congrats!", "Results", JOptionPane.PLAIN_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(new JFrame(), "Sorry, you failed with " + numWrong + " wrong.",
                        "Results", JOptionPane.PLAIN_MESSAGE);
            }
        }

        public static void main(String[] args) {
            initiate();
        }
}