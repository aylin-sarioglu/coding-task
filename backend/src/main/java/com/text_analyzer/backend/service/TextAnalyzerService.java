package com.text_analyzer.backend.service;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.text_analyzer.backend.dto.AnalyzerMode;
import com.text_analyzer.backend.dto.TextAnalyzerResponseDTO;

@Service
public class TextAnalyzerService {

    private final RefactoredTextAnalyzer localTextAnalyzer;

    public TextAnalyzerService(RefactoredTextAnalyzer localTextAnalyzer) {
        this.localTextAnalyzer = localTextAnalyzer;
    }

    public TextAnalyzerResponseDTO analyzeText(String inputText, AnalyzerMode analyzerMode) {
        Map<Character, Integer> analyzerReport = localTextAnalyzer.analyze(inputText, analyzerMode);

        return new TextAnalyzerResponseDTO(inputText, analyzerMode, analyzerReport);
    }

}
