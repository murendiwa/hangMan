import java.util.List;
import java.util.Random;

/**
 * Holds the list of possible secret words and picks one at random.
 * In Stage 2 you can replace the hardcoded list with words loaded from words.txt.
 */
public class WordBank {
    // Keep words lowercase and letters only (no spaces or hyphens) for now.
    private final List<String> words = List.of(
            "java", "hangman", "variable", "database", "keyboard",
            "software", "network", "compiler", "function", "algorithm",
            "security", "interface", "developer", "programming", "encryption"
    );

    private final Random random = new Random();
    private String lastWord;

    /** Returns a random word from the list (never the same word twice in a row). */
    public String getRandomWord() {
        String word;
        do {
            word = words.get(random.nextInt(words.size()));
        } while (word.equals(lastWord) && words.size() > 1);
        lastWord = word;
        return word;
    }
}
