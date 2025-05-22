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

    private TextAnalyzerResponseDTO analyzeTextForConsonants(String input) {
        HashMap<Character, Integer> consonants = new HashMap<>();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 'a'
                    && chars[i] != 'A'
                    && chars[i] != 'e'
                    && chars[i] != 'E'
                    && chars[i] != 'i'
                    && chars[i] != 'I'
                    && chars[i] != 'o'
                    && chars[i] != 'O'
                    && chars[i] != 'u'
                    && chars[i] != 'U') {
                Character stringCharacter = Character.toUpperCase(chars[i]);
                if (consonants.containsKey(stringCharacter)) {
                    Integer num = consonants.get(stringCharacter);
                    num++;
                    consonants.put(stringCharacter, num);
                } else {
                    consonants.put(stringCharacter, 1);
                }
            }
        }
        consonants.entrySet().forEach(entrySet -> {
            System.out.println("Letter '" + entrySet.getKey() + "' appears " + entrySet.getValue() + " times");
        });

        TextAnalyzerResponseDTO responseDTO = new TextAnalyzerResponseDTO(input, AnalyzerMode.CONSONANTS, consonants);
        return responseDTO;
    }

    private static boolean isVowel(char inputChar) {
        return VOWEL_SET.contains(Character.toUpperCase(inputChar));
    }

    public static void main(String[] args) {
        analyzeTextForVowels("!%$");
    }
}
