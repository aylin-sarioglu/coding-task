package com.text_analyzer.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for requesting a text analysis.
 */

public class TextAnalyzerRequestDTO {
    /**
     * The input text that should be analyzed. Must be not be empty.
     */
    @NotBlank
    private String inputText;

    /**
     * The mode of the given analysis, which can either be VOWELS or CONSONANTS.
     */
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
