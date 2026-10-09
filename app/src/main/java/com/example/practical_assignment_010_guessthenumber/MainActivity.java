package com.example.practical_assignment_010_guessthenumber;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GuessNumberGame game;
    private NumberGenerator generator;

    private EditText editGuess;
    private Button buttonCheck;
    private Button buttonNewGame;
    private TextView textResult;
    private TextView textAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeViews();

        generator = new RandomNumberGenerator();
        game = new GuessNumberGame(generator.generate());

        buttonCheck.setOnClickListener(v -> checkGuess());
        buttonNewGame.setOnClickListener(v -> startNewGame());

        updateAttempts();
        textResult.setText("Вгадайте число від 1 до 100");
    }

    private void initializeViews() {
        editGuess = findViewById(R.id.editGuess);
        buttonCheck = findViewById(R.id.buttonCheck);
        buttonNewGame = findViewById(R.id.buttonNewGame);
        textResult = findViewById(R.id.textResult);
        textAttempts = findViewById(R.id.textAttempts);
    }

    private void checkGuess() {

        String input = editGuess.getText().toString().trim();

        if (input.isEmpty()) {
            showMessage("Введіть число");
            return;
        }

        int number;

        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            showMessage("Введіть коректне ціле число");
            return;
        }

        if (number < 1 || number > 100) {
            showMessage("Число повинно бути від 1 до 100");
            return;
        }

        GuessResult result = game.checkGuess(number);

        showGuessResult(result);
        updateAttempts();

        editGuess.setText("");
    }

    private void showGuessResult(GuessResult result) {

        switch (result) {

            case TOO_LOW:
                textResult.setText("Загадане число більше");
                break;

            case TOO_HIGH:
                textResult.setText("Загадане число менше");
                break;

            case CORRECT:
                textResult.setText(
                        "Вітаємо! Ви вгадали число за "
                                + game.getAttempts()
                                + " спроб!"
                );

                buttonCheck.setEnabled(false);
                editGuess.setEnabled(false);
                break;

            case GAME_OVER:
                textResult.setText(
                        "Гру завершено. Почніть нову гру."
                );
                break;
        }
    }

    private void startNewGame() {

        int newSecretNumber = generator.generate();

        game.startNewGame(newSecretNumber);

        editGuess.setText("");
        editGuess.setEnabled(true);
        buttonCheck.setEnabled(true);

        textResult.setText("Нова гра! Вгадайте число від 1 до 100");

        updateAttempts();
    }

    private void updateAttempts() {
        textAttempts.setText(
                "Кількість спроб: " + game.getAttempts()
        );
    }

    private void showMessage(String message) {
        Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT
        ).show();
    }
}