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

    /**
     * Performs a text analysis on the given input using the specified
     * {@link AnalyzerMode}.
     * Delegates the analysis task to the refactored text analyzer and wraps the
     * result
     * in a {@link TextAnalyzerResponseDTO} object containing the original input,
     * the analysis mode, and the character frequency report.
     *
     * @param inputText    the text to be analyzed
     * @param analyzerMode the mode indicating whether to analyze for vowels or
     *                     consonants
     * @return a {@link TextAnalyzerResponseDTO} containing the input text, selected
     *         mode, and the resulting character frequency map
     */
    public TextAnalyzerResponseDTO analyzeText(String inputText, AnalyzerMode analyzerMode) {
        Map<Character, Integer> analyzerReport = localTextAnalyzer.analyze(inputText, analyzerMode);

        return new TextAnalyzerResponseDTO(inputText, analyzerMode, analyzerReport);
    }

}
