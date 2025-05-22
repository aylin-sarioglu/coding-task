package com.text_analyzer.backend;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.text_analyzer.backend.dto.AnalyzerType;
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
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerType.VOWELS);

        Map<Character, Integer> vowels = result.getVowelCount();
        assertEquals(2, vowels.get('A'));
        assertEquals(2, vowels.get('E'));
        assertEquals(2, vowels.get('I'));
        assertEquals(2, vowels.get('O'));
        assertEquals(2, vowels.get('U'));
        assertNull(result.getConsonantsCount());
    }

    @Test
    void testAnalyzeConsonantsOnly() {
        String input = "BCDbcd";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerType.CONSONANTS);

        Map<Character, Integer> consonants = result.getConsonantsCount();
        assertEquals(2, consonants.get('B'));
        assertEquals(2, consonants.get('C'));
        assertEquals(2, consonants.get('D'));
        assertNull(result.getVowelCount());
    }

    @Test
    void testAnalyzeWithMixedCharactersVowels() {
        String input = "Hello World";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerType.VOWELS);

        Map<Character, Integer> vowels = result.getVowelCount();
        assertEquals(1, vowels.get('E'));
        assertEquals(2, vowels.get('O'));
        assertEquals(0, vowels.get('A'));
        assertEquals(0, vowels.get('I'));
        assertEquals(0, vowels.get('U'));
        assertNull(result.getConsonantsCount());
    }

    @Test
    void testAnalyzeWithMixedCharactersConsonants() {
        String input = "Hello World";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerType.CONSONANTS);

        Map<Character, Integer> consonants = result.getConsonantsCount();
        assertEquals(1, consonants.get('H'));
        assertEquals(3, consonants.get('L'));
        assertEquals(1, consonants.get('W'));
        assertEquals(1, consonants.get('R'));
        assertEquals(1, consonants.get('D'));
        assertNull(result.getVowelCount());
    }

    @Test
    void testAnalyzeWithMixedSpecialCharactersVowels() {
        String input = "Hello World!";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerType.VOWELS);

        Map<Character, Integer> vowels = result.getVowelCount();
        assertEquals(1, vowels.get('E'));
        assertEquals(2, vowels.get('O'));
        assertEquals(0, vowels.get('A'));
        assertEquals(0, vowels.get('I'));
        assertEquals(0, vowels.get('U'));
        assertTrue(!vowels.containsKey('!'));
        assertNull(result.getConsonantsCount());
    }

    @Test
    void testAnalyzeWithMixedSpecialCharactersConsonants() {
        String input = "Hello World";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerType.CONSONANTS);

        Map<Character, Integer> consonants = result.getConsonantsCount();
        assertEquals(1, consonants.get('H'));
        assertEquals(3, consonants.get('L'));
        assertEquals(1, consonants.get('W'));
        assertEquals(1, consonants.get('R'));
        assertEquals(1, consonants.get('D'));
        assertTrue(!consonants.containsKey('!'));
        assertNull(result.getVowelCount());
    }

    @Test
    void testAnalyzeWithOnlySpecialCharactersVowels() {
        String input = "!%$§";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerType.VOWELS);

        assertTrue(result.getVowelCount().isEmpty());
        assertNull(result.getConsonantsCount());
    }

    @Test
    void testAnalyzeWithOnlySpecialCharactersConsonants() {
        String input = "!%$§";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerType.CONSONANTS);

        assertTrue(result.getConsonantsCount().isEmpty());
        assertNull(result.getVowelCount());
    }

    @Test
    void testAnalyzeWithEmptyStringVowels() {
        String input = "";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerType.VOWELS);

        assertTrue(result.getVowelCount().isEmpty());
        assertNull(result.getConsonantsCount());
    }

    @Test
    void testAnalyzeWithEmptyStringConsonants() {
        String input = "";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerType.CONSONANTS);

        assertTrue(result.getConsonantsCount().isEmpty());
        assertNull(result.getVowelCount());
    }

    @Test
    void testAnalyzeWithWhiteSpaceStringVowels() {
        String input = "   ";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerType.VOWELS);

        assertTrue(result.getVowelCount().isEmpty());
        assertNull(result.getConsonantsCount());
    }

    @Test
    void testAnalyzeWithWhiteSpaceStringConsonants() {
        String input = "   ";
        TextAnalyzerResponseDTO result = analyzer.analyze(input, AnalyzerType.CONSONANTS);

        assertTrue(result.getConsonantsCount().isEmpty());
        assertNull(result.getVowelCount());
    }

}
