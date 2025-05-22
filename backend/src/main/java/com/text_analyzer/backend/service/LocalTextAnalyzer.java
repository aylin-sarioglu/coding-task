package com.text_analyzer.backend.service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;
import com.text_analyzer.backend.dto.AnalyzerMode;

@Component
public class LocalTextAnalyzer {
    private final Set<Character> VOWEL_SET = new LinkedHashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U'));

    public Map<Character, Integer> analyze(String input, AnalyzerMode type) {
        switch (type) {
            case AnalyzerMode.VOWELS:
            default:
                return analyzeTextForVowels(input);
            case AnalyzerMode.CONSONANTS:
                return analyzeTextForConsonants(input);
        }
    }

    private Map<Character, Integer> analyzeTextForVowels(String input) {
        if (input == null || input.isBlank()) {
            return new LinkedHashMap<>();
        }

        LinkedHashMap<Character, Integer> vowelsCountMap = new LinkedHashMap<>();

        for (char vowel : VOWEL_SET) {
            vowelsCountMap.put(vowel, 0);
        }

        for (char inputChar : input.toUpperCase().toCharArray()) {
            if (isVowel(inputChar)) {
                vowelsCountMap.computeIfPresent(inputChar, (vowel, count) -> ++count);
            }
        }

        return vowelsCountMap;

    }

    private Map<Character, Integer> analyzeTextForConsonants(String input) {
        if (input == null || input.isBlank()) {
            return new LinkedHashMap<>();
        }

        LinkedHashMap<Character, Integer> consonantsCountMap = new LinkedHashMap<>();

        for (char inputChar : input.toUpperCase().toCharArray()) {
            if (!isVowel(inputChar) && Character.isLetter(inputChar)) {
                consonantsCountMap.compute(inputChar, (consonant, count) -> count == null ? 1 : ++count);

            }
        }

        return consonantsCountMap;

    }

    private boolean isVowel(char inputChar) {
        return VOWEL_SET.contains(Character.toUpperCase(inputChar));
    }
}
