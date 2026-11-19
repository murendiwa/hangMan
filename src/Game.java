import java.util.Set;
import java.util.TreeSet;

/**
 * The rules and state of one round of Hangman.
 * This class never prints or reads input, so the same logic can later
 * be reused by a JavaFX screen without changing anything here.
 */
public class Game {
    /** The three possible outcomes of a guess. */
    public enum GuessResults {CORRECT, WRONG, WORNG, REPEAT}

    public static final int MAX_LIVES = 5;

    private final String secretWord;
    private final Set<Character> guessedLetters = new TreeSet<>(); //Treeset keeps letters in A-Z order
    private int livesLeft = MAX_LIVES;

    public Game(String secretWord){
        this.secretWord= secretWord.toLowerCase();
    }

    /**
     * Processes one guess.
     * - REPEAT: the letter was already guessed (no life lost)
     * - CORRECT: the letter is in the word
     * - WRONG: the letter is not in the word (costs a life)
     */

    public GuessResults guess(char letter){
        letter = Character.toLowerCase(letter);

        if (guessedLetters.contains(letter)){
            return GuessResults.REPEAT;
        }

        guessedLetters.add(letter);
        if (secretWord.indexOf(letter) >=0){
            return GuessResults.CORRECT;
        }
        livesLeft--;
        return GuessResults.WRONG;
    }
    /** Returns the word with unguessed letters hidden */

    public String getMaskedWord(){
        StringBuilder masked = new StringBuilder();
        for (char c: secretWord.toCharArray()){
            masked.append(guessedLetters.contains(c)?c:'_').append(" ");
        }
        return masked.toString().trim();
    }
    boolean isWon(){
        for (char c : secretWord.toCharArray()){
            if (!guessedLetters.contains(c)){
                return false;
            }
        }
        return true;
    }

    public boolean isLost(){
        return livesLeft <= 0;
    }
    public int getLivesLeft(){
        return livesLeft;
    }

    public Set<Character> getGuessedLetters(){
        return guessedLetters;
    }

    /** Only reveal this when the round is over! */
    public String getSecretWord() {
        return secretWord;
    }
}
