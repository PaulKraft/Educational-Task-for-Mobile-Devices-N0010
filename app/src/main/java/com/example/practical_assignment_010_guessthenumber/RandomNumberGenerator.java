package com.example.practical_assignment_010_guessthenumber;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    private final Random random = new Random();

    @Override
    public int generate() {
        // Твоя реалізація
        return random.nextInt(100) + 1;
    }
}
