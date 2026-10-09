package com.example.practical_assignment_010_guessthenumber;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuessNumberGameTest {

    @Test
    public void correctGuessReturnsCorrect() {
        GuessNumberGame game = new GuessNumberGame(50);

        assertEquals(
                GuessResult.CORRECT,
                game.checkGuess(50)
        );
    }

    @Test
    public void lowerGuessReturnsTooLow() {
        GuessNumberGame game = new GuessNumberGame(50);

        assertEquals(
                GuessResult.TOO_LOW,
                game.checkGuess(25)
        );
    }

    @Test
    public void higherGuessReturnsTooHigh() {
        GuessNumberGame game = new GuessNumberGame(50);

        assertEquals(
                GuessResult.TOO_HIGH,
                game.checkGuess(75)
        );
    }

    @Test
    public void attemptsIncreaseAfterGuess() {
        GuessNumberGame game = new GuessNumberGame(50);

        game.checkGuess(25);
        game.checkGuess(75);

        assertEquals(2, game.getAttempts());
    }

    @Test
    public void gameIsOverAfterCorrectGuess() {
        GuessNumberGame game = new GuessNumberGame(50);

        game.checkGuess(50);

        assertTrue(game.isGameOver());
    }

    @Test
    public void newGameIsNotOver() {
        GuessNumberGame game = new GuessNumberGame(50);

        assertFalse(game.isGameOver());
    }

    @Test
    public void guessAfterWinReturnsGameOver() {
        GuessNumberGame game = new GuessNumberGame(50);

        game.checkGuess(50);

        assertEquals(
                GuessResult.GAME_OVER,
                game.checkGuess(75)
        );
    }

    @Test
    public void attemptsDoNotIncreaseAfterWin() {
        GuessNumberGame game = new GuessNumberGame(50);

        game.checkGuess(25);
        game.checkGuess(50);
        game.checkGuess(75);

        assertEquals(2, game.getAttempts());
    }

    @Test
    public void startNewGameResetsState() {
        GuessNumberGame game = new GuessNumberGame(50);

        game.checkGuess(25);
        game.checkGuess(50);

        game.startNewGame(75);

        assertEquals(0, game.getAttempts());
        assertFalse(game.isGameOver());

        assertEquals(
                GuessResult.CORRECT,
                game.checkGuess(75)
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void secretNumberBelowRangeIsRejected() {
        new GuessNumberGame(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void secretNumberAboveRangeIsRejected() {
        new GuessNumberGame(101);
    }
}