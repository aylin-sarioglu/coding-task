import { Injectable } from '@angular/core';
import {
  TextAnalyzer,
  TextAnalyzerRequest,
  TextAnalyzerResponse,
} from './text-analyzer';

@Injectable({
  providedIn: 'root',
})
export class OfflineTextAnalyzer implements TextAnalyzer {
  async analyze(data: TextAnalyzerRequest): Promise<TextAnalyzerResponse> {
    const result = this.analyzeText(data);

    return result;
  }

  private readonly VOWEL_SET: Set<string> = new Set(['A', 'E', 'I', 'O', 'U']);
  private readonly EMPTY_ANALYSIS_RESPONSE: TextAnalyzerResponse = {
    inputText: '',
    analyzerMode: 'VOWELS',
    report: new Map(),
  };

  public analyzeText(request: TextAnalyzerRequest): TextAnalyzerResponse {
    switch (request.analyzerMode) {
      case 'VOWELS':
        return {
          inputText: request.inputText,
          analyzerMode: request.analyzerMode,
          report: this.analyzeTextForVowels(request.inputText),
          isOffline: true,
        } satisfies TextAnalyzerResponse;
      case 'CONSONANTS':
        return {
          inputText: request.inputText,
          analyzerMode: request.analyzerMode,
          report: this.analyzeTextForConsonants(request.inputText),
          isOffline: true,
        } satisfies TextAnalyzerResponse;
      default:
        // optional defensive case
        console.error('Unknown analyzer mode');
        return this.EMPTY_ANALYSIS_RESPONSE;
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
