package com.text_analyzer.backend;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.text_analyzer.backend.dto.AnalyzerMode;
import com.text_analyzer.backend.service.RefactoredTextAnalyzer;

public class RefactoredTextAnalyzerTest {
    private RefactoredTextAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new RefactoredTextAnalyzer();
    }

    @Test
    void testAnalyzeVowelsOnly() {
        String input = "AaEeIiOoUu";
        Map<Character, Integer> result = analyzer.analyze(input, AnalyzerMode.VOWELS);

        assertEquals(2, result.get('A'));
        assertEquals(2, result.get('E'));
        assertEquals(2, result.get('I'));
        assertEquals(2, result.get('O'));
        assertEquals(2, result.get('U'));
    }

    @Test
    void testAnalyzeConsonantsOnly() {
        String input = "BCDbcd";
        Map<Character, Integer> result = analyzer.analyze(input, AnalyzerMode.CONSONANTS);

        assertEquals(2, result.get('B'));
        assertEquals(2, result.get('C'));
        assertEquals(2, result.get('D'));
    }

    @Test
    void testAnalyzeWithMixedCharactersVowels() {
        String input = "Hello World";
        Map<Character, Integer> result = analyzer.analyze(input, AnalyzerMode.VOWELS);

        assertEquals(1, result.get('E'));
        assertEquals(2, result.get('O'));
        assertEquals(0, result.get('A'));
        assertEquals(0, result.get('I'));
        assertEquals(0, result.get('U'));
    }

    @Test
    void testAnalyzeWithMixedCharactersConsonants() {
        String input = "Hello World";
        Map<Character, Integer> result = analyzer.analyze(input, AnalyzerMode.CONSONANTS);

        assertEquals(1, result.get('H'));
        assertEquals(3, result.get('L'));
        assertEquals(1, result.get('W'));
        assertEquals(1, result.get('R'));
        assertEquals(1, result.get('D'));
    }

    @Test
    void testAnalyzeWithMixedSpecialCharactersVowels() {
        String input = "Hello World!";
        Map<Character, Integer> result = analyzer.analyze(input, AnalyzerMode.VOWELS);

        assertEquals(1, result.get('E'));
        assertEquals(2, result.get('O'));
        assertEquals(0, result.get('A'));
        assertEquals(0, result.get('I'));
        assertEquals(0, result.get('U'));
        assertFalse(result.containsKey('!'));

    }

    @Test
    void testAnalyzeWithMixedSpecialCharactersConsonants() {
        String input = "Hello World";
        Map<Character, Integer> result = analyzer.analyze(input, AnalyzerMode.CONSONANTS);

        assertEquals(1, result.get('H'));
        assertEquals(3, result.get('L'));
        assertEquals(1, result.get('W'));
        assertEquals(1, result.get('R'));
        assertEquals(1, result.get('D'));
        assertFalse(result.containsKey('!'));

    }

    @Test
    void testAnalyzeWithOnlySpecialCharactersVowels() {
        String input = "!%$§";
        Map<Character, Integer> result = analyzer.analyze(input, AnalyzerMode.VOWELS);

        assertEquals(0, result.get('E'));
        assertEquals(0, result.get('O'));
        assertEquals(0, result.get('A'));
        assertEquals(0, result.get('I'));
        assertEquals(0, result.get('U'));

    }

    @Test
    void testAnalyzeWithOnlySpecialCharactersConsonants() {
        String input = "!%$§";
        Map<Character, Integer> result = analyzer.analyze(input, AnalyzerMode.CONSONANTS);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAnalyzeWithEmptyStringVowels() {
        String input = "";
        Map<Character, Integer> result = analyzer.analyze(input, AnalyzerMode.VOWELS);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAnalyzeWithEmptyStringConsonants() {
        String input = "";
        Map<Character, Integer> result = analyzer.analyze(input, AnalyzerMode.CONSONANTS);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAnalyzeWithWhiteSpaceStringVowels() {
        String input = "   ";
        Map<Character, Integer> result = analyzer.analyze(input, AnalyzerMode.VOWELS);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAnalyzeWithWhiteSpaceStringConsonants() {
        String input = "   ";
        Map<Character, Integer> result = analyzer.analyze(input, AnalyzerMode.CONSONANTS);

        assertTrue(result.isEmpty());
    }

}
