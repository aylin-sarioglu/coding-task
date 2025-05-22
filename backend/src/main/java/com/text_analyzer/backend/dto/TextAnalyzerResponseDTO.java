package com.text_analyzer.backend.dto;

import java.util.Map;

public class TextAnalyzerResponseDTO {
    private String inputText;
    private AnalyzerType analyzerType;
    private Map<Character, Integer> report;

    public TextAnalyzerResponseDTO(String inputText, AnalyzerType analyzerType, Map<Character, Integer> report) {
        this.inputText = inputText;
        this.analyzerType = analyzerType;
        this.report = report;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public AnalyzerType getAnalyzerType() {
        return analyzerType;
    }

    public void setAnalyzerType(AnalyzerType analyzerType) {
        this.analyzerType = analyzerType;
    }

    public Map<Character, Integer> getReport() {
        return report;
    }

    public void setReport(Map<Character, Integer> report) {
        this.report = report;
    }

}
