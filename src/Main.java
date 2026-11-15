/**
 * Entry point: wires the pieces together and starts the game.
 */
public class Main {
    public static void main(String[] args) {
        WordBank wordBank = new WordBank();
        ConsoleUI ui = new ConsoleUI(wordBank);
        ui.start();
    }
}