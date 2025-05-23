package com.text_analyzer.backend.dto;

import java.util.Map;

/**
 * DTO for results of the text analysis.
 */
public class TextAnalyzerResponseDTO {
    /**
     * The input text that is analyzed.
     */
    private String inputText;

    /**
     * The mode of the given analysis, which can either be VOWELS or CONSONANTS.
     */
    private AnalyzerMode analyzerMode;

    /**
     * The results of the analyis, providing the letter and how many times it
     * occurs in the given input text.
     */
    private Map<Character, Integer> report;

    public TextAnalyzerResponseDTO(String inputText, AnalyzerMode analyzerMode, Map<Character, Integer> report) {
        this.inputText = inputText;
        this.analyzerMode = analyzerMode;
        this.report = report;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public AnalyzerMode getAnalyzerMode() {
        return analyzerMode;
    }

    public void setAnalysisMode(AnalyzerMode analyzerMode) {
        this.analyzerMode = analyzerMode;
    }

    public Map<Character, Integer> getReport() {
        return report;
    }

    public void setReport(Map<Character, Integer> report) {
        this.report = report;
    }

}
