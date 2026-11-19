import java.util.Scanner;

public class ConsoleUI {
    // One drawing per number of wrong guesses (0 to 6).
    private static final String[] STAGES = {
            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
    };

    private final WordBank wordBank;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(WordBank wordBank){
        this.wordBank = wordBank;
    }

    /** Runs rounds until the player chooses to stop. */
    public void start(){
        System.out.println("====HANGMAN====");

        boolean playAgain;
        do{
            playRound();
            playAgain = askPlayAgain();
        } while (playAgain);
        System.out.println("Dankii For Playing!!");
    }
    private void playRound()
    {
        Game game = new Game(wordBank.getRandomWord());
        while(!game.isWon() && !game.isLost())
        {
            printStatus(game);
            char letter = readLetter();

        switch (game.guess(letter))
        {
            case CORRECT -> System.out.println("Good guess!'" + letter + "'is the word.");
            case WORNG -> System.out.println("Sorry,'" + letter + "'is not the word.");
            case REPEAT -> System.out.println("You already guessed'" + letter + "'. Try a different letter.");
         }
        }
        // Round is over: show the final state and the result.
        printStatus(game);
        if (game.isWon()){
            System.out.println("\nYou won! The word was \"" + game.getSecretWord() + "\".");
        }else{
            System.out.println("\n You lost! The wod was \"" + game.getSecretWord() + "\"");
        }
    }

    private void printStatus(Game game) {
        int wrongGuesses = Game.MAX_LIVES - game.getLivesLeft();
        System.out.println();
        System.out.println(STAGES[wrongGuesses]);
        System.out.println("Word:    " + game.getMaskedWord());
        System.out.println("Lives:   " + game.getLivesLeft());
        System.out.println("Guessed: " + game.getGuessedLetters());
    }
    /** Keeps asking until the player types exactly one letter. */
    private char readLetter() {
        while (true) {
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine().trim();

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                return input.charAt(0);
            }
            System.out.println("Please enter a single letter (a-z).");
        }
    }
    private boolean askPlayAgain(){
        while(true){
            System.out.print("\n Play Again? (Y/N):");
            String input = scanner.nextLine().trim().toLowerCase();

            if ("Y".equals(input)){
                return true;
            }
            if ("N".equals(input)){
                return false;
            }
            System.out.println("Please type y or n");
        }
    }
}
