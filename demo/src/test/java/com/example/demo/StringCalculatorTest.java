package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private final StringCalculator calculator = new StringCalculator();

    @Test
    void testEmptyString() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    void testSingleNumber() {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    void testTwoNumbers() {
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    void testMultipleNumbers() {
        assertEquals(6, calculator.add("1,2,3"));
    }

    @Test
    void testNewLineAsDelimiter() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    void testCustomDelimiter() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    void testCustomDelimiterWithDifferentCharacter() {
        assertEquals(10, calculator.add("//|\n1|2|3|4"));
    }

    @Test
    void testNegativeNumbersThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.add("1,-2,3,-4"));
        assertEquals("Negative numbers not allowed: [-2, -4]", exception.getMessage());
    }

    @Test
    void testCustomDelimiterWithNegativeNumbers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.add("//;\n-1;2;-3"));
        assertEquals("Negative numbers not allowed: [-1, -3]", exception.getMessage());
    }
    
    @Test
    void testSupportForLargeNumbers() {
        assertEquals(1001, calculator.add("1000,1"));
        assertEquals(3001, calculator.add("1000,1000,1000,1"));
    }

    @Test
    void testInvalidNumberFormatThrowsException() {
        Exception exception = assertThrows(NumberFormatException.class, () -> calculator.add("2,3,A"));
        assertEquals("For input string: \"A\"", exception.getMessage());
    }

  }
