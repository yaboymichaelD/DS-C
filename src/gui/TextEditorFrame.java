package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

import spelling.Dictionary;
import spelling.SpellChecker;

public class TextEditorFrame extends JFrame {

    private static final int TEXT_AREA_ROWS = 10;
    private static final int TEXT_AREA_COLUMNS = 50;

    private JTextArea textArea;

    private JButton button1;
    private JButton button2;

    private Dictionary dictionary;

    public TextEditorFrame(Dictionary dictionary) {

        this.dictionary = dictionary;

        setTitle("Spelling Checker Application");

        textArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);

        textArea.setEditable(true);  // textArea can be edited

        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel panel = new JPanel(new GridLayout(1, 2)); // to allow 2 buttons

        button1 = new JButton("Open file...");
        button2 = new JButton("Spell check");

        panel.add(button1);
        panel.add(button2);
        ButtonListener bListener = new ButtonListener();
        button1.addActionListener(bListener);
        button2.addActionListener(bListener);

        getContentPane().add(scrollPane, "Center");

        getContentPane().add(panel, "South");

        addWindowListener(new MyWindowListener());

        pack();
    }

    private class MyWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
        }

        @Override
        public void windowClosed(WindowEvent e) {
            System.out.println("ending");
            System.exit(0);
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button == button1) {

                try {
                    JFileChooser fileChooser = new JFileChooser();
                    int returnVal = fileChooser.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        System.out.println("You chose to open this file: " +
                                fileChooser.getSelectedFile().getName());
                    }

                    File inputFile = fileChooser.getSelectedFile();

                    Scanner in = new Scanner(inputFile);

                    String line;

                    textArea.setText("");
                    while (in.hasNextLine()) {
                        line = in.nextLine();
                        textArea.append(line);
                        textArea.append("\n");
                    }
                } catch (FileNotFoundException exc) {
                    System.out.println("File not found");
                    exc.printStackTrace();
                    System.exit(1);
                }
            } else if (button == button2) {
                //do spell check
                String text = textArea.getText();
                SpellChecker spellChecker = new SpellChecker(dictionary);
                List<String> list = spellChecker.doSpellCheck(text);
                System.out.println("Misspellings:");
                System.out.println(list);
            }
        }
    }
}
