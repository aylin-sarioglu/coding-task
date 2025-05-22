package com.text_analyzer.backend.dto;

import java.util.Map;

public class TextAnalysisResponseDTO {
    private Map<Character, Integer> vowelCount;
    private Map<Character, Integer> consonantsCount;

    public TextAnalysisResponseDTO(Map<Character, Integer> vowelCount,
            Map<Character, Integer> consonantsCount) {
        this.vowelCount = vowelCount;
        this.consonantsCount = consonantsCount;
    }

    public Map<Character, Integer> getVowelCount() {
        return vowelCount;
    }

    public void setVowelCount(Map<Character, Integer> vowelCount) {
        this.vowelCount = vowelCount;
    }

    public Map<Character, Integer> getConsonantsCount() {
        return consonantsCount;
    }

    public void setConsonantsCount(Map<Character, Integer> consonantsCount) {
        this.consonantsCount = consonantsCount;
    }

}
