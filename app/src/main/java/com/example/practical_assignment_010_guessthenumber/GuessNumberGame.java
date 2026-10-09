package com.example.practical_assignment_010_guessthenumber;

public class GuessNumberGame {

    private int secretNumber;
    private int attempts;
    private boolean gameOver;

    public GuessNumberGame(int secretNumber) {
        startNewGame(secretNumber);
    }

    public GuessResult checkGuess(int number) {

        if (gameOver) {
            return GuessResult.GAME_OVER;
        }

        attempts++;

        if (number == secretNumber) {
            gameOver = true;
            return GuessResult.CORRECT;
        }

        if (number < secretNumber) {
            return GuessResult.TOO_LOW;
        }

        return GuessResult.TOO_HIGH;
    }

    public void startNewGame(int secretNumber) {
        if (secretNumber < 1 || secretNumber > 100) {
            throw new IllegalArgumentException(
                    "Secret number must be between 1 and 100"
            );
        }

        this.secretNumber = secretNumber;
        this.attempts = 0;
        this.gameOver = false;
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}