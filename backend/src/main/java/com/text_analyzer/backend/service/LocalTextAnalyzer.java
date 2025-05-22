package com.text_analyzer.backend.service;

import java.util.HashMap;
import java.util.Set;
import org.springframework.stereotype.Component;
import com.text_analyzer.backend.dto.AnalyzerMode;
import com.text_analyzer.backend.dto.TextAnalyzerResponseDTO;

@Component
public class LocalTextAnalyzer {
    private static final Set<Character> VOWEL_SET = Set.of('A', 'E', 'I', 'O', 'U');

    public TextAnalyzerResponseDTO analyze(String input, AnalyzerMode type) {
        switch (type) {
            case AnalyzerMode.VOWELS:
                return analyzeTextForVowels(input);
            case AnalyzerMode.CONSONANTS:
                return analyzeTextForConsonants(input);
            default:
                return analyzeTextForVowels(input);
        }
    }

    private static TextAnalyzerResponseDTO analyzeTextForVowels(String input) {
        if (input == null || input.isBlank()) {
            return new TextAnalyzerResponseDTO(input, AnalyzerMode.VOWELS, new HashMap<>());
        }

        HashMap<Character, Integer> vowelsCountMap = new HashMap<>();

        for (char vowel : VOWEL_SET) {
            vowelsCountMap.put(vowel, 0);
        }

        for (char inputChar : input.toUpperCase().toCharArray()) {
            if (isVowel(inputChar)) {
                vowelsCountMap.computeIfPresent(inputChar, (vowel, count) -> ++count);
            }
        }

        return new TextAnalyzerResponseDTO(input, AnalyzerMode.VOWELS, vowelsCountMap);

    }

    private static TextAnalyzerResponseDTO analyzeTextForConsonants(String input) {
        if (input == null || input.isBlank()) {
            return new TextAnalyzerResponseDTO(input, AnalyzerMode.CONSONANTS, new HashMap<>());
        }

        HashMap<Character, Integer> consonantsCountMap = new HashMap<>();

        for (char inputChar : input.toUpperCase().toCharArray()) {
            if (!isVowel(inputChar) && Character.isLetter(inputChar)) {
                consonantsCountMap.compute(inputChar, (consonant, count) -> count == null ? 1 : ++count);

            }
        }

        return new TextAnalyzerResponseDTO(input, AnalyzerMode.CONSONANTS, consonantsCountMap);

    }

    private static boolean isVowel(char inputChar) {
        return VOWEL_SET.contains(Character.toUpperCase(inputChar));
    }

    public static void main(String[] args) {
        analyzeTextForConsonants("Hallo");
    }
}
