package com.text_analyzer.backend.service;

import java.util.Map;
import org.springframework.stereotype.Service;

import com.text_analyzer.backend.dto.AnalyzerType;
import com.text_analyzer.backend.dto.TextAnalyzerResponseDTO;

@Service
public class TextAnalyzerService {

    private final LocalTextAnalyzer localTextAnalyzer;

    public TextAnalyzerService(LocalTextAnalyzer localTextAnalyzer) {
        this.localTextAnalyzer = localTextAnalyzer;
    }

    public TextAnalyzerResponseDTO analyzeOffline(String inputText, AnalyzerType type) {
        // TODO: call analyze offline method here
        Map<Character, Integer> exampleVowelCount = Map.of(
                'a', 3,
                'e', 5);
        Map<Character, Integer> exampleConsonantsCount = Map.of(
                'h', 3,
                'l', 5);

        // return localAnalyzer.analyze(text, type);
        TextAnalyzerResponseDTO responseDTO = new TextAnalyzerResponseDTO(exampleVowelCount, exampleConsonantsCount);
        return responseDTO;

    }

    public TextAnalyzerResponseDTO analyzeOnline(String inputText, AnalyzerType type) {
        // TODO: call analyze online method / REST CALL

        Map<Character, Integer> exampleVowelCount = Map.of(
                'a', 3,
                'e', 5);
        Map<Character, Integer> exampleConsonantsCount = Map.of(
                'h', 3,
                'l', 5);

        TextAnalyzerResponseDTO responseDTO = new TextAnalyzerResponseDTO(exampleVowelCount, exampleConsonantsCount);
        return responseDTO;
    }

}
