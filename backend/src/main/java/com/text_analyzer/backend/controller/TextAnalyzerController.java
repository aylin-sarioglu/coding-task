package com.text_analyzer.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.text_analyzer.backend.dto.AnalyzerMode;
import com.text_analyzer.backend.dto.TextAnalyzerRequestDTO;
import com.text_analyzer.backend.dto.TextAnalyzerResponseDTO;
import com.text_analyzer.backend.service.TextAnalyzerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TextAnalyzerController {

    private TextAnalyzerService textAnalyzerService;

    public TextAnalyzerController(TextAnalyzerService analyzerService) {
        this.textAnalyzerService = analyzerService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<TextAnalyzerResponseDTO> analyzeText(@Valid @RequestBody TextAnalyzerRequestDTO request) {

        String inputText = request.getInputText();
        AnalyzerMode mode = request.getAnalyzerMode();

        TextAnalyzerResponseDTO result = textAnalyzerService.analyzeText(inputText, mode);

        return ResponseEntity.ok(result);
    }

}
