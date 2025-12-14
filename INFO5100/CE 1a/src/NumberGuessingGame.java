import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int choice;

        do {
            // Display Menu
            System.out.println("\n===== NUMBER GUESSING GAME =====");
            System.out.println("1. Start Game");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    int randomNumber = rand.nextInt(10) + 1; // 1 to 10
                    boolean isCorrect = false;

                    System.out.println("\nNew Game Started! Guess a number between 1 and 10.");

                    // 5 attempts
                    for (int attempt = 1; attempt <= 5; attempt++) {

                        System.out.print("Attempt " + attempt + ": Enter your guess: ");
                        int guess = sc.nextInt();

                        if (guess > randomNumber) {
                            System.out.println("Too High!");
                        } else if (guess < randomNumber) {
                            System.out.println("Too Low!");
                        } else {
                            System.out.println("Correct! You guessed it!");
                            isCorrect = true;
                            break; // End the round immediately
                        }
                    }

                    if (!isCorrect) {
                        System.out.println("Sorry, you're out of attempts.");
                        System.out.println("The correct number was: " + randomNumber);
                    }

                    break;

                case 2:
                    System.out.println("\nExiting the game... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please choose 1 or 2.");
            }

        } while (choice != 2);

        sc.close();
    }
}
