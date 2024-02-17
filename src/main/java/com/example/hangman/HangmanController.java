package com.example.hangman;

import com.example.hangman.domain.Word;

public class HangmanController {

    Word word;

    public HangmanController() {
    }

    public void setWord(String newWordAsString) {
        word = new Word(newWordAsString);
        if (word.contains(' ')) {
            guessLetter(" ");
        }
    }

    public Word getWord() {
        return word;
    }

    public String getWordAsString() {
        StringBuilder s = new StringBuilder();
        for (char c : word.getLetters()) {
            s.append(c);
        }
        return s.toString();
    }

    public int getWordLength() {
        return word.getLetters().length;
    }

    public String getWordGuessingProgress() {
        StringBuilder s = new StringBuilder();
        for (char c : word.getLetters()) {
            if (word.getGuessedLetters().contains(c)) {
                s.append(c);
            } else {
                s.append('_');
            }
            s.append(' ');
        }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }

    public void guessLetter(String letter) {
        if (gameOver()) {
            return;
        }
        char letterAsChar = letter.charAt(0);
        if(word.getGuessedLetters().contains(letterAsChar)) {
            return;
        }
        word.addGuessedLetter(letterAsChar);
        if (word.contains(letterAsChar)) {
            word.addCorrectLetter(letterAsChar);
        } else {
            word.addMissedLetter(letterAsChar);
        }
    }

    public boolean gameOver() {
        if (word.getMissedLetters().size() == Word.MAX_MISSES) {
            return true;
        }
        for (char c : word.getLetters()) {
            if (!word.getCorrectLetters().contains(c)) {
                return false;
            }
        }
        return true;
    }


}