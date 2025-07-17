
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author fredd
 */
public class Main1 {
    private static final String[] WORDS = {"java", "python", "computer", "program", "hangman"};
    private static int MAX_TRIES;

    private String wordToGuess;
    private StringBuilder currentGuess;
    private int triesLeft;
    private char[] incorrectGuess;
    private Scanner scanner;
    private int maxWordLength;

    //main function using global variables maxWordLength and maxTries
    public Main1(int maxWordLength, int maxTries) {
        this.maxWordLength = maxWordLength;
        scanner = new Scanner(System.in);
        //calls function getRandomWord and gets a word within the maxWordLength and returns as wordToGuess
        wordToGuess = getRandomWord(maxWordLength);
        //uses user input and if letter is in word the '_' is replaced with the guessed letter for all every time it is in the word
        currentGuess = new StringBuilder(wordToGuess.length());
        for (int i = 0; i < wordToGuess.length(); i++) {
            currentGuess.append("_");
        }
        //sets triesLeft and incorrectGuess equal to variable maxTries
        triesLeft = maxTries;
        incorrectGuess = new char[maxTries];
    }
    //function to get a random word from the array of words
    private String getRandomWord(int maxLength) {
        String[] wordsWithinLength = new String[WORDS.length];
        int count = 0;
        for (String word : WORDS) {
            if (word.length() <= maxLength) {
                wordsWithinLength[count] = word;
                count++;
            }
        }
        return wordsWithinLength[(int) (Math.random() * count)];
    }
    //main function that outputs to terminal
    public void play() {
        //while loop allows user to guess letters whilst there are still tries left or if there are'_' left in the word
        while (triesLeft > 0 && currentGuess.indexOf("_") != -1) {
            System.out.println("Current guess: " + currentGuess);
            System.out.println("Tries left: " + triesLeft);
            System.out.print("Incorrect letters: ");
            //if i is less that remaining guesses then the incorrectly guessed letters are output
            for (int i = 0; i < MAX_TRIES - triesLeft; i++) {
                System.out.print(incorrectGuess[i] + " ");
            }
            System.out.println();
            //input guess for user
            System.out.print("Enter a letter: ");
            char guess = scanner.next().charAt(0);
            System.out.println();
            //calls the updateGuess function and passes the character guessed
            // updatGuess returns false then it is an incorrect guess and triesLeft is reduced
            if (!updateGuess(guess)) {
                incorrectGuess[MAX_TRIES - triesLeft] = guess;
                triesLeft--;
                System.out.println("Incorrect guess!");
            }
        }
        // after the while loop the if statements checks if there is any '_', if there isn't then the output shows that you beat the game
        // else the output states you ran out of guesses. both show the word to guess after
        if (currentGuess.indexOf("_") == -1) {
            System.out.println("Congratulations! You've guessed the word: " + wordToGuess);
        } else {
            System.out.println("Sorry, you've run out of tries. The word was: " + wordToGuess);
        }
    }

    private boolean updateGuess(char guess) {
        boolean found = false;
        //for the length of the word the loop runs
        for (int i = 0; i < wordToGuess.length(); i++) {
            // if statment checks if the guess is in the word. if it is the found is changed from false to true and returned.
            if (wordToGuess.charAt(i) == guess) {
                currentGuess.setCharAt(i, guess);
                found = true;
            }
        }
        return found;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //sets an integer maxium word length to private variable maxWordLength
        System.out.print("Enter maximum word length: ");
        int maxWordLength = scanner.nextInt();
        
        System.out.print("Choose difficulty level (easy/medium/hard): ");
        String difficulty = scanner.next().toLowerCase(); 
        //this switch case statement uses the users input to decide the difficulty using variable MAX_TRIES, if there isn't a correct input
        //then the MAX_TRIES is set to medium, this is then passed onto Main function
        switch (difficulty) {
            case "easy":
                MAX_TRIES = 8;
                break;
            case "medium":
                MAX_TRIES = 6;
                break;
            case "hard":
                MAX_TRIES = 4;
                break;
            default:
                System.out.println("Invalid difficulty level. Setting difficulty to medium.");
                MAX_TRIES = 6;
                break;
        }
        //creates an instance of the Main function called game
        Main1 game = new Main1(maxWordLength, MAX_TRIES);
        //instance of passes through play function
        game.play();
    }    
}
