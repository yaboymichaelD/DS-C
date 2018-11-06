package spelling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryAVLTree implements Dictionary {
    private AVLTree<String> dictionary;

    public DictionaryAVLTree(String filename) {
        load(filename);
    }

    @Override
    public void load(String fileName) {
        dictionary = new AVLTree();
        try {
            // Prepare to read from the file, using a Scanner object
            File file = new File(fileName);
            Scanner in = new Scanner(file);

            // Read each word until end of file is reached
            while (in.hasNextLine()) {
                // Read one word from the file
                String word = in.nextLine();
                dictionary.add(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    @Override
    public boolean isWord(String s) {
        return dictionary.contains(s);
    }

    @Override
    public boolean addWord(String word) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}