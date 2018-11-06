package spelling;

public interface Dictionary {
    /**
     * Loads the dictionary from a file
     *
     * @param file The file used for the dictionary
    */
    public void load(String file);
    
    /**
     * Checks if s is in the dictionary.
     *
     * @param s The word to check
     * @return true if s is in the dictionary
    */
    public boolean isWord(String s);

    /**
     * Add this word to the dictionary.
     *
     * @param word The word to add
     * @return true if the word was added to the dictionary (it wasn't already
     * there).
     */
    public boolean addWord(String word);

    /**
     * Returns the number of words in the dictionary
     */
    public int size();

}