import { Injectable } from '@angular/core';
import {
  TextAnalyzer,
  TextAnalyzerRequest,
  TextAnalyzerResponse,
} from './text-analyzer-interface';

@Injectable({
  providedIn: 'root',
})

export class OfflineTextAnalyzer implements TextAnalyzer {
  async analyze(data: TextAnalyzerRequest): Promise<TextAnalyzerResponse> {
    const result = this.analyzeText(data);

    return result;
  }

  private readonly VOWEL_SET: Set<string> = new Set(['A', 'E', 'I', 'O', 'U']);

  public analyzeText(request: TextAnalyzerRequest): TextAnalyzerResponse {
    switch (request.analyzerMode) {
      case 'VOWELS':
        return {
          inputText: request.inputText,
          analyzerMode: request.analyzerMode,
          report: this.analyzeTextForVowels(request.inputText),
        } satisfies TextAnalyzerResponse;
      case 'CONSONANTS':
        return {
          inputText: request.inputText,
          analyzerMode: request.analyzerMode,
          report: this.analyzeTextForConsonants(request.inputText),
        } satisfies TextAnalyzerResponse;
    }
  }

  private analyzeTextForVowels(input: string): Map<string, number> {
    if (!input || input.trim() === '') {
      return new Map();
    }

    const vowelsCountMap = new Map<string, number>();

    for (const vowel of this.VOWEL_SET) {
      vowelsCountMap.set(vowel, 0);
    }

    for (const char of input.toUpperCase()) {
      if (this.isVowel(char)) {
        vowelsCountMap.set(char, (vowelsCountMap.get(char) || 0) + 1);
      }
    }

    return vowelsCountMap;
  }

  private analyzeTextForConsonants(input: string): Map<string, number> {
    const LETTER_EXPRESSION: RegExp = /[A-Z]/;

    if (!input || input.trim() === '') {
      return new Map();
    }

    const consonantsCountMap = new Map<string, number>();

    for (const char of input.toUpperCase()) {
      if (!this.isVowel(char) && LETTER_EXPRESSION.test(char)) {
        consonantsCountMap.set(char, (consonantsCountMap.get(char) || 0) + 1);
      }
    }

    return consonantsCountMap;
  }

  private isVowel(char: string): boolean {
    return this.VOWEL_SET.has(char.toUpperCase());
  }
}
