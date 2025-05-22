package com.text_analyzer.backend.dto;

import java.util.Map;

public class TextAnalyzerResponseDTO {
    private String inputText;
    private AnalyzerMode analyzerMode;
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
