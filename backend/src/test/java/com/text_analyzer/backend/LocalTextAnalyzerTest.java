package com.text_analyzer.backend;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.text_analyzer.backend.dto.AnalyzerMode;
import com.text_analyzer.backend.dto.TextAnalyzerResponseDTO;
import com.text_analyzer.backend.service.LocalTextAnalyzer;

public class LocalTextAnalyzerTest {
    private LocalTextAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new LocalTextAnalyzer();
    }

    @Test
    void testAnalyzeVowelsOnly() {
        String input = "AaEeIiOoUu";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerMode.VOWELS);

        Map<Character, Integer> vowels = result.getReport();
        assertEquals(AnalyzerMode.VOWELS, result.getAnalyzerMode());
        assertEquals(input, result.getInputText());
        assertEquals(2, vowels.get('A'));
        assertEquals(2, vowels.get('E'));
        assertEquals(2, vowels.get('I'));
        assertEquals(2, vowels.get('O'));
        assertEquals(2, vowels.get('U'));
    }

    @Test
    void testAnalyzeConsonantsOnly() {
        String input = "BCDbcd";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerMode.CONSONANTS);

        Map<Character, Integer> consonants = result.getReport();
        assertEquals(AnalyzerMode.CONSONANTS, result.getAnalyzerMode());
        assertEquals(input, result.getInputText());
        assertEquals(2, consonants.get('B'));
        assertEquals(2, consonants.get('C'));
        assertEquals(2, consonants.get('D'));
    }

    @Test
    void testAnalyzeWithMixedCharactersVowels() {
        String input = "Hello World";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerMode.VOWELS);

        Map<Character, Integer> vowels = result.getReport();
        assertEquals(AnalyzerMode.VOWELS, result.getAnalyzerMode());
        assertEquals(input, result.getInputText());
        assertEquals(1, vowels.get('E'));
        assertEquals(2, vowels.get('O'));
        assertEquals(0, vowels.get('A'));
        assertEquals(0, vowels.get('I'));
        assertEquals(0, vowels.get('U'));
    }

    @Test
    void testAnalyzeWithMixedCharactersConsonants() {
        String input = "Hello World";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerMode.CONSONANTS);

        Map<Character, Integer> consonants = result.getReport();
        assertEquals(AnalyzerMode.CONSONANTS, result.getAnalyzerMode());
        assertEquals(input, result.getInputText());
        assertEquals(1, consonants.get('H'));
        assertEquals(3, consonants.get('L'));
        assertEquals(1, consonants.get('W'));
        assertEquals(1, consonants.get('R'));
        assertEquals(1, consonants.get('D'));
    }

    @Test
    void testAnalyzeWithMixedSpecialCharactersVowels() {
        String input = "Hello World!";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerMode.VOWELS);

        Map<Character, Integer> vowels = result.getReport();
        assertEquals(AnalyzerMode.VOWELS, result.getAnalyzerMode());
        assertEquals(input, result.getInputText());
        assertEquals(1, vowels.get('E'));
        assertEquals(2, vowels.get('O'));
        assertEquals(0, vowels.get('A'));
        assertEquals(0, vowels.get('I'));
        assertEquals(0, vowels.get('U'));
        assertTrue(!vowels.containsKey('!'));

    }

    @Test
    void testAnalyzeWithMixedSpecialCharactersConsonants() {
        String input = "Hello World";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerMode.CONSONANTS);

        Map<Character, Integer> consonants = result.getReport();
        assertEquals(AnalyzerMode.CONSONANTS, result.getAnalyzerMode());
        assertEquals(input, result.getInputText());
        assertEquals(1, consonants.get('H'));
        assertEquals(3, consonants.get('L'));
        assertEquals(1, consonants.get('W'));
        assertEquals(1, consonants.get('R'));
        assertEquals(1, consonants.get('D'));
        assertTrue(!consonants.containsKey('!'));

    }

    @Test
    void testAnalyzeWithOnlySpecialCharactersVowels() {
        String input = "!%$§";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerMode.VOWELS);

        assertEquals(AnalyzerMode.VOWELS, result.getAnalyzerMode());
        assertEquals(input, result.getInputText());
        assertTrue(result.getReport().isEmpty());

    }

    @Test
    void testAnalyzeWithOnlySpecialCharactersConsonants() {
        String input = "!%$§";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerMode.CONSONANTS);

        assertEquals(AnalyzerMode.CONSONANTS, result.getAnalyzerMode());
        assertEquals(input, result.getInputText());
        assertTrue(result.getReport().isEmpty());
    }

    @Test
    void testAnalyzeWithEmptyStringVowels() {
        String input = "";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerMode.VOWELS);

        assertEquals(AnalyzerMode.VOWELS, result.getAnalyzerMode());
        assertEquals(input, result.getInputText());
        assertTrue(result.getReport().isEmpty());
    }

    @Test
    void testAnalyzeWithEmptyStringConsonants() {
        String input = "";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerMode.CONSONANTS);

        assertEquals(AnalyzerMode.CONSONANTS, result.getAnalyzerMode());
        assertEquals(input, result.getInputText());
        assertTrue(result.getReport().isEmpty());
    }

    @Test
    void testAnalyzeWithWhiteSpaceStringVowels() {
        String input = "   ";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerMode.VOWELS);

        assertEquals(AnalyzerMode.VOWELS, result.getAnalyzerMode());
        assertEquals(input, result.getInputText());
        assertTrue(result.getReport().isEmpty());
    }

    @Test
    void testAnalyzeWithWhiteSpaceStringConsonants() {
        String input = "   ";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerMode.CONSONANTS);

        assertEquals(AnalyzerMode.CONSONANTS, result.getAnalyzerMode());
        assertEquals(input, result.getInputText());
        assertTrue(result.getReport().isEmpty());
    }

}
