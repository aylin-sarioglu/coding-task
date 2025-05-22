package com.text_analyzer.backend.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

import com.text_analyzer.backend.dto.AnalyzerMode;
import com.text_analyzer.backend.dto.TextAnalyzerResponseDTO;

@Component
public class LocalTextAnalyzer {
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

    private TextAnalyzerResponseDTO analyzeTextForVowels(String input) {
        int numA = 0;
        int numE = 0;
        int numI = 0;
        int numO = 0;
        int numU = 0;
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
            if (chars[i] == 'a' || chars[i] == 'A')
                numA++;
            if (chars[i] == 'e' || chars[i] == 'E')
                numE++;
            if (chars[i] == 'i' || chars[i] == 'I')
                numI++;
            if (chars[i] == 'o' || chars[i] == 'O')
                numO++;
            if (chars[i] == 'u' || chars[i] == 'U')
                numU++;
        }
        System.out.println("Letter 'A' appears " + numA + " times");
        System.out.println("Letter 'E' appears " + numE + " times");
        System.out.println("Letter 'I' appears " + numI + " times");
        System.out.println("Letter 'O' appears " + numO + " times");
        System.out.println("Letter 'U' appears " + numU + " times");

        Map<Character, Integer> vowelCount = Map.of(
                'A', numA,
                'E', numE,
                'I', numI,
                'O', numO,
                'U', numU);

        TextAnalyzerResponseDTO responseDTO = new TextAnalyzerResponseDTO(input, AnalyzerMode.VOWELS, vowelCount);
        return responseDTO;

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

}
