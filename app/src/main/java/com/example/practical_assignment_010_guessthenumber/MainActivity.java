package com.example.practical_assignment_010_guessthenumber;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private Random random = new Random();
    private int secretNumber;
    private int attempts = 0;

    public void startNewGame() {

        secretNumber = generateNumber();
//        secretNumber = 50;
        attempts = 0;

    }

    private int generateNumber() {
//        return random.nextInt(100) + 1;
        return 50;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        startNewGame();

        Button buttonNewGame = findViewById(R.id.buttonNewGame);
        Button buttonCheck = findViewById(R.id.buttonCheck);
        EditText editTextGuess = findViewById(R.id.editTextGuess);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonNewGame.setOnClickListener(v -> {
            startNewGame();
            editTextGuess.setText("");
            buttonCheck.setEnabled(true);
            editTextGuess.setEnabled(true);
            Toast.makeText(
                    MainActivity.this,
                    "Starting a new game!",
                    Toast.LENGTH_SHORT
            ).show();
        });

        buttonCheck.setOnClickListener(v -> {
            String userInput = editTextGuess.getText().toString();


            if (userInput.isEmpty()){
                userInput = "empty";
                Toast.makeText(
                        MainActivity.this,
                        "Your number іs empty: " + userInput,
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }else {


                int number = Integer.parseInt(userInput);

                attempts++;


                if (secretNumber == number) {
                    Toast.makeText(
                            MainActivity.this,
                            "win answer: " + number + ", Attempts: " + attempts,
                            Toast.LENGTH_LONG
                    ).show();

                    buttonCheck.setEnabled(false);
                    editTextGuess.setEnabled(false);

                } else if (secretNumber < number) {
                    Toast.makeText(
                            MainActivity.this,
                            "Your number is larger. ",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (secretNumber > number) {
                    Toast.makeText(
                            MainActivity.this,
                            "Your number is smaller.",
                            Toast.LENGTH_SHORT
                    ).show();
                }

            }


        });
    }
}