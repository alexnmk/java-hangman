/**
 * Hangman.java
 * - player wins if successfully guess a hidden word within eight guess attempts
 * - player loses if no guess attempts left
 * - number of guesses are not deducted if player makes correct or repeated guesses
 *
 * @author	Alex Ng
 * @version	0.0.1
 * @since	18/11/22
 */

package com.fdmgroup.assessment.hangman;

import java.util.Random;
import java.util.Scanner;

public class Hangman {

	// Declaring pre-populated word array and number of guesses
	static final String[] RANDOM_STRINGS = { "BOOK", "CAT", "COMPUTER", "POWER", "BANANA" };
	static int guessNum = 8;
	
	// Select word randomly
	static final String RANDOM_STRING = selectWord(RANDOM_STRINGS);
	
	// Declare a list of previous guesses
	static String[] guessInputs = new String[RANDOM_STRING.length() + guessNum];

	// Hide randomly selected word
	static String hiddenString = hideWord(RANDOM_STRING);
	static int hiddenStringLength = hiddenString.length();

	
	/**
	 * Select word randomly from a pre-populated list of words
	 * 
	 * @param strings pre-populated word list
	 * @return        a string randomly selected from the word list
	 */
	static String selectWord(String[] strings) {
		Random rand = new Random();
		int upperbound = strings.length;
		int int_random = rand.nextInt(upperbound);
		return strings[int_random];
	}

	
	/**
	 * Hide the chosen word
	 * 
	 * @param string randomly selected string
	 * @return       a word replaced by "-" of same word length
	 */
	static String hideWord(String string) {
		return string.replaceAll(".", "-");
	}

	
	/**
	 * Display guessed letters
	 * 
	 * @param guessInput   guess made by player
	 * @param randomString a randomly selected string
	 * @param hiddenString the selected string displayed with "-"
	 * @return             the hidden string with correctly guessed letters displayed
	 */
	static String displayGuessedLetters(String guessInput, String randomString, String hiddenString) {
		int guessInputPos = randomString.indexOf(guessInput);
		while (guessInputPos >= 0) {
			hiddenString = hiddenString.substring(0, guessInputPos) + guessInput
					+ hiddenString.substring(guessInputPos + 1);
			guessInputPos = randomString.indexOf(guessInput, guessInputPos + 1);
		}
		return hiddenString;
	}
	

	/**
	 * Check if guess is repeated
	 * 
	 * @param guess               guess made by player
	 * @param guessInputs         an array of previous unique guess
	 * @return <code>true</code>  if the guess matches with data in the array;
	 *         <code>false</code> otherwise
	 */
	static boolean isGuessRepeated(String guess, String[] guessInputs) {
		for (String guessInput : guessInputs) {
			if (guess.equals(guessInput)) {
				return true;
			}
		}
		return false;
	}
	

	/**
	 * Print a game that allows players to guess a hidden word within eight attempts
	 * @param args a string array that stores command line arguments
	 */
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		// Game start
		System.out.println("Welcome to Hangman!");

		/*
		 * Loop over the number of guesses 
		 * Number of guesses decrements when player makes a guess attempt 
		 * Loop ends when there is no guess attempts or the player guesses the whole word
		 */
		while (guessNum > 0) {
			// Winning condition
			if (!hiddenString.contains("-")) {
				System.out.println("You guessed the word: " + RANDOM_STRING + "\nYou win.");
				break;
			}

			// Proceed with guessing
			System.out.println("The word now looks like this: " + hiddenString);

			if (guessNum > 1) {
				System.out.println("You have " + guessNum + " guesses left.");
			}
			if (guessNum == 1) {
				System.out.println("You have only one guess left.");
			}

			// Reading input from player
			System.out.print("Your guess: ");
			String guessInput = in.nextLine();
			guessInput = guessInput.toUpperCase();

			// A loop to check if the guess is repeated with previous attempts
			while (isGuessRepeated(guessInput, guessInputs)) {
				System.out.print("It’s a previously guessed letter. Please guess again: ");
				guessInput = in.nextLine();
				guessInput = guessInput.toUpperCase();
			}

			// Add player's guess to a list with previous attempts
			guessInputs[guessInputs.length - guessNum - hiddenStringLength] = guessInput;

			// To check if the guess is correct
			if (RANDOM_STRING.contains(guessInput)) {
				System.out.println("That guess is correct.");
				hiddenString = displayGuessedLetters(guessInput, RANDOM_STRING, hiddenString);
				hiddenStringLength--;
			} else {
				System.out.println("There are no " + guessInput + "\'s in the word.");
				guessNum--; // Deduct number of guesses for wrong attempt
			}
		}

		// Losing condition
		if (guessNum == 0) {
			System.out.println("You're completely hung.\nThe word was: " + RANDOM_STRING + "\nYou lose.");
		}

		// Close Scanner
		in.close();
	}

}
