package com.text_analyzer.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TextAnalysisRequestDTO {
    @NotBlank
    private String inputText;

    @NotNull
    private AnalysisMode analysisMode;

    @NotNull
    private AnalysisType analysisType;

    public TextAnalysisRequestDTO(@NotBlank String inputText, @NotNull AnalysisMode analysisMode,
            @NotNull AnalysisType analysisType) {
        this.inputText = inputText;
        this.analysisMode = analysisMode;
        this.analysisType = analysisType;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public AnalysisMode getAnalysisMode() {
        return analysisMode;
    }

    public void setAnalysisMode(AnalysisMode analysisMode) {
        this.analysisMode = analysisMode;
    }

    public AnalysisType getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(AnalysisType analysisType) {
        this.analysisType = analysisType;
    }

}
