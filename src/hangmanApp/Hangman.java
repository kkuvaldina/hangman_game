package hangmanApp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hangman game. The user has 6 lives to guess the word by guessing the letters.
 * 
 * @author kseniia
 *
 */
public class Hangman {
	static boolean win = false;
	
	/**
	 * Main class.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String word = "Mississippi";
		String guessWord = word.toLowerCase();
		int numOfLives = 6;
		ArrayList<Integer> guessesIndexes = new ArrayList<>();
		
		System.out.println("Hangman game!");
		System.out.println("Guess the word!\n");
		
		//6 lives to play the game
		while (numOfLives != 0 || win) {
			printLetters(guessWord, guessesIndexes, numOfLives);
			if(win) break;
			
			String guessLetter = scanner.next();
			int index = guessWord.indexOf(guessLetter);
			
			//if no index was found then the guess was wrong, subtract a life
			if (index < 0) numOfLives--;
			
			//if index was found search for another one and add all indexes into ArrayList
			while (index >= 0) {
				guessesIndexes.add(index);
			    index = guessWord.indexOf(guessLetter, index + 1);
			}	
		}	

		//the game is over when all lives have been used
		if (!win) System.out.println("\nThe word was " + word + ", try again!");
	}

	/**
	 * Prints guessed letters and num of lives left.
	 * @param word		word to be guessed
	 * @param indexes	array of all guessed indexed in the word
	 * @param lives		num of lives left
	 */
	private static void printLetters(String word, ArrayList<Integer> indexes, int lives) {
		int guessWordNumOfLetters = word.length();

		for (int i = 0; i < guessWordNumOfLetters; i++) {
			if (!indexes.contains(i)) {
				System.out.print("_ ");
			} else {
				System.out.print(word.charAt(i) + " ");
			}

		}
		System.out.printf("	(lives left: %d)%n", lives);

		//If the number of indexes in the ArrayList matches the number of letters in the word 
		//then the user has won
		if (indexes.size() == guessWordNumOfLetters) {
			System.out.println("\nCongratulations!");
			win = true;
		} 
		
		if (lives > 0 && indexes.size() != guessWordNumOfLetters) System.out.print("\nYour guess: ");
	}
}
