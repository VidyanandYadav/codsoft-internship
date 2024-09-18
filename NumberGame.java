import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 10;
    private static int roundsWon = 0;
    private static int totalAttempts = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;

        do {
            playAgain = playRound(scanner, random);
            if (playAgain) {
                System.out.println("Do you want to play another round? (yes/no)");
                String response = scanner.next().trim().toLowerCase();
                playAgain = response.equals("yes");
            }
        } while (playAgain);

        System.out.println("Game over!");
        System.out.println("Rounds won: " + roundsWon);
        System.out.println("Total attempts: " + totalAttempts);
        double averageAttempts = roundsWon > 0 ? (double) totalAttempts / roundsWon : 0;
        System.out.printf("Average attempts per round: %.2f\n", averageAttempts);

        scanner.close();
    }

    private static boolean playRound(Scanner scanner, Random random) {
        int numberToGuess = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        int attemptsLeft = MAX_ATTEMPTS;
        boolean guessedCorrectly = false;

        System.out.println("New round! Guess the number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
        while (attemptsLeft > 0 && !guessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            totalAttempts++;

            if (userGuess < MIN_NUMBER || userGuess > MAX_NUMBER) {
                System.out.println("Please guess a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
            } else if (userGuess < numberToGuess) {
                System.out.println("Too low! Attempts left: " + (attemptsLeft - 1));
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Attempts left: " + (attemptsLeft - 1));
            } else {
                guessedCorrectly = true;
                roundsWon++;
                System.out.println("Congratulations! You've guessed the number.");
                System.out.println("Attempts used: " + (MAX_ATTEMPTS - attemptsLeft + 1));
                break;
            }
            attemptsLeft--;
        }

        if (!guessedCorrectly) {
            System.out.println("Sorry, you've used all your attempts. The number was " + numberToGuess + ".");
        }

        // Return true if the user wants to play again
        return true;
    }
}

