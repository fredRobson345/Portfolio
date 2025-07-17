/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author fredd
 */
public class Hangmane9 {
    private static final String[] WORDS = {"bird", "blue", "cake", "desk", "easy","face", "gold", "harp", 
"idea", "jazz", "kite", "lawn", "maze", "nose", "opal", "pink", 
"quiz", "rain", "star", "tuna", "apple", "beach", "chair", "dusty", 
"eagle", "frost", "grape", "happy", "ivory", "jumbo", "knock", 
"lemon", "marsh", "noble", "ocean", "piano", "queen", "rival", 
"snake", "tiger", "banana", "castle", "donkey", "eleven", "forest", 
"guitar", "helmet", "island", "jacket", "kitten", "laptop", "museum", 
"noodle", "orange", "puzzle", "rocket", "sunset", "tongue", "umbrella", 
"violet","awesome", "bicycle", "chicken", "dolphin", "elephant",
 "glasses", "holiday", "jasmine", "kitchen", "library", "monster",
 "necktie", "octopus", "pancake", "quality", "rainbow", "station",
 "tropical", "unicorn", "vampire", "birthday", "colorful", "daughter",
 "elephant", "football", "hospital", "jewellery", "keyboard",
 "language", "mountain", "notebook", "pipeline", "quantity",
 "raindrop", "sunlight", "template", "umbrella", "victoria",
 "wildfire", "xenonize", "adventure", "beautiful", "challenge",
 "definitely", "education", "fantastic", "generation",
 "happiness", "incredible", "jewellery", "knowledge",
 "literally", "mysterious", "necessary", "operation",
 "powerful", "question", "restaurant", "satisfaction",
 "technology"
};
    private String wordToGuess;
    private StringBuilder currentGuess;
    private int triesLeft;
    private char[] incorrectGuess;

    public Hangmane9() {
        // Initialize game state
        wordToGuess = getRandomWord();
        currentGuess = new StringBuilder(wordToGuess.length());
        for (int i = 0; i < wordToGuess.length(); i++) {
            currentGuess.append("_");
        }
        triesLeft = 8; // You can change the number of tries as needed
        incorrectGuess = new char[triesLeft];
    }

    private String getRandomWord() {
        return WORDS[(int) (Math.random() * WORDS.length)];
    }

    public boolean makeGuess(char guess) {
        boolean found = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                currentGuess.setCharAt(i, guess);
                found = true;
            }
        }
        if (!found) {
            incorrectGuess[triesLeft - 1] = guess;
            triesLeft--;
        }
        return found;
    }

    public String getCurrentGuess() {
        return currentGuess.toString();
    }

    public int getTriesLeft() {
        return triesLeft;
    }

    public boolean isGameFinished() {
        return triesLeft == 0 || !currentGuess.toString().contains("_");
    }

    public boolean isGameWon() {
        return currentGuess.toString().equals(wordToGuess);
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public void reset() {
        wordToGuess = getRandomWord();
        currentGuess = new StringBuilder(wordToGuess.length());
        for (int i = 0; i < wordToGuess.length(); i++) {
            currentGuess.append("_");
        }
        triesLeft = 8; // Reset tries left
        incorrectGuess = new char[triesLeft]; // Reset incorrect guesses
    }
    
    public char[] getIncorrectGuess() {
        return incorrectGuess;
    }
}
