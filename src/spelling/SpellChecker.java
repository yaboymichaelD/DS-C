package spelling;

import java.util.ArrayList;
import java.util.List;

public class SpellChecker {

    private Dictionary dictionary;

    public SpellChecker(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List doSpellCheck(String text) {
        String[] words;
        // parse text into words using a regular expression
        // the character pattern that separates words is any sequence of
        // characters other than letters, numbers and apostrophe
        // This strips off punctuation marks
        words = text.split("[^A-Za-z0-9']+");

        List listOfMissspeltWords = new ArrayList();
        for (String word : words) {
            if (!dictionary.isWord(word.toLowerCase())) {
                if (!listOfMissspeltWords.contains(word)) {
                    listOfMissspeltWords.add(word);
                }
            }
        }

        return listOfMissspeltWords;
    }
}
