package spelling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DictionaryArrayList implements Dictionary {
    private List<String> dictionary;
    private String[] arrayDictionary;

    public DictionaryArrayList(String filename) {
        load(filename);
    }

    @Override
    public void load(String fileName) {
        dictionary = new ArrayList<String>();

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
            arrayDictionary = dictionary.toArray(new String[dictionary.size()]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    @Override
    public boolean isWord(String s) {
        int result = Arrays.binarySearch(arrayDictionary, s);
        if (result >= 0)
            return true;
        else return false;
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
