package com.example.practical_assignment_010_guessthenumber;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberGeneratorTest {

    @Test
    public void fixedGeneratorReturnsExpectedNumber() {
        NumberGenerator generator = new FixedNumberGenerator(50);

        assertEquals(50, generator.generate());
    }

    @Test
    public void randomGeneratorReturnsNumberInRange() {
        NumberGenerator generator = new RandomNumberGenerator();

        for (int i = 0; i < 1000; i++) {
            int number = generator.generate();

            assertTrue(number >= 1);
            assertTrue(number <= 100);
        }
    }
}