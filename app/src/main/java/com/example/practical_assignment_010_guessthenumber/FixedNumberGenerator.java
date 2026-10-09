package com.example.practical_assignment_010_guessthenumber;

public class FixedNumberGenerator implements NumberGenerator {

    private final int number;

    public FixedNumberGenerator(int number) {
        this.number = number;
    }

    @Override
    public int generate() {
        // Твоя реалізація
        return this.number;
    }
}
