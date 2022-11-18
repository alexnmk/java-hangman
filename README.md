# java-hangman

Hangman is a paper and pencil guessing game for two or more players. 
One player thinks of a word, phrase or sentence and the other tries to guess it by suggesting letters within a certain number of guesses.


# Features
- The player is given 8 guesses, deducted only if the guessed letter does not exist in the hidden word
- The hidden word should be displayed, with a dash marking any hidden letters
- A list of previously guessed letters should also be displayed
- If the player guesses a previously guessed letter, display a message indicating as such and allow them to guess again
- The hidden word will be selected at random from a pre-populated list of words


# Assumptions
- The hidden word is in all uppercase
- Guesses are not case sensitive. That is, if the player guessed ‘A’ it should still reveal all instances of ‘a’
- The player will always give an appropriate input (a single letter)
- Words will not contain any punctuation
- Words will also be a single word (not a phrase)
