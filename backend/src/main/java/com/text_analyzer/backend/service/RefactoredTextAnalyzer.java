package com.text_analyzer.backend.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import com.text_analyzer.backend.dto.AnalyzerMode;

/**
 * The program for calculating how many times letter in given sentence appears.
 * It gives numbers either for vowels or for consonants based on program input.
 */

@Component
public class RefactoredTextAnalyzer {
    private final Set<Character> VOWEL_SET = new LinkedHashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U'));

    private final Map<Character, Integer> EMPTY_ANALYSIS_RESPONSE = new LinkedHashMap<>();

    public Map<Character, Integer> analyze(String input, AnalyzerMode type) {
        switch (type) {
            case AnalyzerMode.VOWELS:
                return analyzeTextForVowels(input);
            case AnalyzerMode.CONSONANTS:
                return analyzeTextForConsonants(input);
            default:
                return EMPTY_ANALYSIS_RESPONSE;
        }
    }

    private Map<Character, Integer> initializeCountMap(Set<Character> characters) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char character : characters) {
            countMap.put(character, 0);
        }
        return countMap;
    }

    private Map<Character, Integer> countCharacters(
            String input,
            Predicate<Character> filterFn,
            Set<Character> prefillSet) {
        if (input == null || input.isBlank()) {
            return EMPTY_ANALYSIS_RESPONSE;
        }

        Map<Character, Integer> countMap = (prefillSet != null)
                ? initializeCountMap(prefillSet)
                : new LinkedHashMap<>();

        for (char character : input.toUpperCase().toCharArray()) {
            if (filterFn.test(character)) {
                countMap.put(character, countMap.getOrDefault(character, 0) + 1);
            }
        }

        return countMap;
    }

    private Map<Character, Integer> countCharactersWithout(String input,
            Predicate<Character> filterFn) {
        return countCharacters(input, filterFn, null);

    }

    private Map<Character, Integer> analyzeTextForVowels(String input) {
        return countCharacters(input, this::isVowel, VOWEL_SET);

    }

    private Map<Character, Integer> analyzeTextForConsonants(String input) {
        Predicate<Character> isConsonant = ch -> !isVowel(ch) && Character.isLetter(ch);
        return countCharactersWithout(input, isConsonant);

    }

    private boolean isVowel(char inputChar) {
        return VOWEL_SET.contains(Character.toUpperCase(inputChar));
    }
}
