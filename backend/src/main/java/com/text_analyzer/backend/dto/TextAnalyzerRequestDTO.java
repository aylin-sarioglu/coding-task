package com.text_analyzer.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TextAnalyzerRequestDTO {
    @NotBlank
    private String inputText;

    @NotNull
    private AnalyzerMode analyzerMode;

    public TextAnalyzerRequestDTO(@NotBlank String inputText, @NotNull AnalyzerMode analyzerMode) {
        this.inputText = inputText;
        this.analyzerMode = analyzerMode;
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

    public void setAnalyzerMode(AnalyzerMode analyzerMode) {
        this.analyzerMode = analyzerMode;
    }

}
