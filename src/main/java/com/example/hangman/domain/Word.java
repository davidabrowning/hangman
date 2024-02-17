package com.example.hangman.domain;

import java.util.HashSet;
import java.util.Set;

public class Word {

    private char[] letters;
    public static final int MAX_MISSES = 7;
    private Set<Character> guessedLetters;
    private Set<Character> correctLetters;
    private Set<Character> missedLetters;

    public Word(String wordAsString) {
        letters = wordAsString.toUpperCase().toCharArray();
        guessedLetters = new HashSet<>();
        correctLetters = new HashSet<>();
        missedLetters = new HashSet<>();
    }

    public char[] getLetters() {
        return letters;
    }

    public void addGuessedLetter(char letter) {
        guessedLetters.add(letter);
    }

    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public void addCorrectLetter(char letter) {
        correctLetters.add(letter);
    }

    public void addMissedLetter(char letter) {
        missedLetters.add(letter);
    }

    public Set<Character> getCorrectLetters() {
        return correctLetters;
    }

    public Set<Character> getMissedLetters() {
        return missedLetters;
    }

    public boolean contains(char letter) {
        for (char c : letters) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }

}
