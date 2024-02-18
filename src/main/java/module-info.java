module com.example.hangman {
    requires javafx.controls;

    opens com.example.hangman to javafx.fxml;
    exports com.example.hangman;
}