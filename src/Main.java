
import com.nexagis.jawbone.Dictionary;
import com.nexagis.jawbone.IndexTerm;
import com.nexagis.jawbone.filter.ExactMatchFilter;
import com.nexagis.jawbone.filter.TermFilter;

import java.util.Iterator;

public class Main {


    // Creates the dictionary and connects it to the database archive
    public static Dictionary createDictionary(String path) {
        Dictionary.initialize(path);
        return Dictionary.getInstance();
    }

    // Checks if the word given is a real English word
    public static boolean wordExists(Dictionary d, String term) {
        TermFilter filter = new ExactMatchFilter(term, true);

        // Get an iterator to the list of nouns
        Iterator<IndexTerm> iter = d.getIndexTermIterator(1, filter);

        return iter.hasNext();
    }

    public static void main(String[] args) {

        // Do not change path
        Dictionary d = createDictionary("./dict");

        for (String word : testWords) {
            System.out.printf("%s: %s%n", word, wordExists(d, word));
            System.out.println(wordExists(d, word));
        }
    }
}