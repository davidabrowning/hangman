package com.example.hangman;

import java.util.ArrayList;
import java.util.List;

import com.example.hangman.domain.Word;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class HangmanApplication extends Application {

    private HangmanController hangmanController;
    private Stage stage;
    private Scene wordInputScene;
    private TextField wordInputText;
    private Scene guessingScene;
    private Label guessingProgressLabel;
    private Label missesRemainingLabel;
    private List<Button> letterButtons;
    private Font fontExtraLarge;
    private Font fontLarge;
    private Font fontMedium;
    private Font fontSmall;

    public static void main(String[] args) {
        Application.launch(HangmanApplication.class);
    }

    @Override
    public void start(Stage newStage) {

        hangmanController = new HangmanController();
        letterButtons = new ArrayList<>();
        fontExtraLarge = new Font("Arial", 40);
        fontLarge = new Font("Arial", 24);
        fontMedium = new Font("Arial", 16);
        fontSmall = new Font("Arial", 12);
        stage = newStage;
        wordInputScene = createWordInputScene();
        guessingScene = createGuessingScene();


        // Configure Stage
        stage.setHeight(600);
        stage.setWidth(1000);
        stage.setTitle("Hangman Game");
        stage.setScene(wordInputScene);
        stage.show();
    }

    private Scene createWordInputScene() {

        // Create components
        Label wordInputLabel = new Label("Please input a word for the other player to guess:");
        wordInputLabel.setFont(fontMedium);
        wordInputText = new TextField();
        wordInputText.setFont(fontMedium);
        Button wordInputSubmitButton = new Button("Let's play");
        wordInputSubmitButton.setFont(fontMedium);

        // Add components to container
        HBox wordInputContainer = new HBox();
        wordInputContainer.getChildren().addAll(wordInputLabel, wordInputText, wordInputSubmitButton);
        wordInputContainer.setAlignment(Pos.CENTER);
        wordInputContainer.setSpacing(10);
        wordInputContainer.setPadding(new Insets(10, 10, 10, 10));

        // Add container to Scene
        Scene wordInputScene = new Scene(wordInputContainer);

        // Add action events
        wordInputText.setOnAction(event -> {
            setWord(wordInputText.getText());
        });
        wordInputSubmitButton.setOnAction(event -> {
            setWord(wordInputText.getText());
        });

        return wordInputScene;
    }

    private void setWord(String newWordAsString) {
        hangmanController.setWord(newWordAsString);
        updateGuessingProgressLabel();
        stage.setScene(guessingScene);
    }

    private void updateGuessingProgressLabel() {
        guessingProgressLabel.setText(hangmanController.getWordGuessingProgress());
        if (hangmanController.gameOver()) {
            missesRemainingLabel.setText("Game over!");
        } else {
            int missesRemaining = Word.MAX_MISSES - hangmanController.getWord().getMissedLetters().size();
            missesRemainingLabel.setText("Misses remaining: " + missesRemaining);
        }
    }

    private Scene createGuessingScene() {

        // Create the components
        guessingProgressLabel = new Label("No word given");
        guessingProgressLabel.setFont(fontExtraLarge);
        missesRemainingLabel = new Label("Misses remaining: " + Word.MAX_MISSES);
        missesRemainingLabel.setFont(fontMedium);
        VBox letterGuessingContainer = createLetterGuessingButtons();
        Button resetGameButton = new Button("Start over");
        resetGameButton.setOnAction(event -> {
            stage.setScene(wordInputScene);
            wordInputText.setText("");
            for (Button letterButton : letterButtons) {
                letterButton.setBackground(Background.fill(Color.WHITESMOKE));
            }
        });

        // Add the components to the container
        VBox guessingContainer = new VBox();
        guessingContainer.getChildren().addAll(guessingProgressLabel, missesRemainingLabel, letterGuessingContainer, resetGameButton);
        guessingContainer.setSpacing(10);
        guessingContainer.setPadding(new Insets(10, 10, 10, 10));
        guessingContainer.setAlignment(Pos.CENTER);

        // Add the container to the Scene
        Scene guessingScene = new Scene(guessingContainer);
        return guessingScene;
    }

    private VBox createLetterGuessingButtons() {

        // Create container
        VBox letterGuessingContainer = new VBox();
        letterGuessingContainer.setAlignment(Pos.CENTER);

        // Create letter buttons, including adding them to the container
        String[] lettersFirstRow = { "Q","W","E","R","T","Y","U","I","O","P" };
        String[] lettersSecondRow = { "A","S","D","F","G","H","J","K","L" };
        String[] lettersThirdRow = { "Z","X","C","V","B","N","M" };
        HBox lettersFirstRowContainer = createLetterGuessesRow(lettersFirstRow);
        HBox lettersSecondRowContainer = createLetterGuessesRow(lettersSecondRow);
        HBox lettersThirdRowContainer = createLetterGuessesRow(lettersThirdRow);
        lettersFirstRowContainer.setAlignment(Pos.CENTER);
        lettersSecondRowContainer.setAlignment(Pos.CENTER);
        lettersThirdRowContainer.setAlignment(Pos.CENTER);

        letterGuessingContainer.getChildren().addAll(lettersFirstRowContainer, lettersSecondRowContainer, lettersThirdRowContainer);
        return letterGuessingContainer;
    }

    private HBox createLetterGuessesRow(String[] lettersRow) {
        HBox lettersRowContainer = new HBox();
        for (String letter : lettersRow) {
            Button letterButton = new Button(letter);
            letterButton.setFont(fontMedium);
            letterButton.setBackground(Background.fill(Color.WHITESMOKE));
            letterButton.setPrefHeight(40);
            letterButton.setPrefWidth(40);
            letterButtons.add(letterButton);
            lettersRowContainer.getChildren().add(letterButton);
            letterButton.setOnAction(event -> {
                hangmanController.guessLetter(letterButton.getText());
                updateGuessingProgressLabel();
                updateLetterButtons();
            });
        }
        return lettersRowContainer;
    }

    private void updateLetterButtons() {
        for(Button letterButton : letterButtons) {
            char letterAsChar = letterButton.getText().charAt(0);
            if (hangmanController.word.getCorrectLetters().contains(letterAsChar)) {
                letterButton.setBackground(Background.fill(Color.LIGHTGREEN));
            }
            if (hangmanController.word.getMissedLetters().contains(letterAsChar)) {
                letterButton.setBackground(Background.fill(Color.GRAY));
            }
        }
    }


}