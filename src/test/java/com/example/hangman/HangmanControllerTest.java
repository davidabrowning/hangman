package com.example.hangman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanControllerTest {

    private HangmanController hc;

    @BeforeEach
    void setup() {
        hc = new HangmanController();
        hc.setWord("ALPHABET");
    }

    @Test
    void wordStartsAsNull() {
        HangmanController newHangmanController = new HangmanController();
        assertNull(newHangmanController.getWord());
    }

    @Test
    void wordIsNotNullAfterBeingSet() {
        assertNotNull(hc.getWord());
    }

    @Test
    void getWordAsStringReturnsStringVersionOfWord() {
        assertEquals(hc.getWordAsString(), "ALPHABET");
    }

    @Test
    void getWordLengthReturnsWordLength() {
        assertEquals(hc.getWordLength(), 8);
    }

    @Test
    void getWordGuessingProgressIsInitiallyBlanks() {
        assertEquals(hc.getWordGuessingProgress(), "_ _ _ _ _ _ _ _");
    }

    @Test
    void wordGuessingAdvancesAfterCorrectGuess() {
        hc.guessLetter("A");
        assertEquals(hc.getWordGuessingProgress(), "A _ _ _ A _ _ _");
    }

    @Test
    void gameIsNotInitiallyOver() {
        assertFalse(hc.gameOver());
    }

    @Test
    void gameIsOverAfterCorrectGuesses() {
        hc.guessLetter("A");
        hc.guessLetter("L");
        hc.guessLetter("P");
        hc.guessLetter("H");
        hc.guessLetter("B");
        hc.guessLetter("E");
        hc.guessLetter("T");
        assertTrue(hc.gameOver());
    }

}