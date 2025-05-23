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

  /**
   * Analyzes the given text based on the specified analyzer mode (either 'VOWELS' or 'CONSONANTS').
   *
   * This method processes the input text locally and generates a character frequency report
   * depending on the selected mode. The result includes the input, mode, report, and an
   * indicator that the analysis was performed offline.
   *
   * @param request An object containing the input text and the desired analyzer mode.
   * @returns A {@link TextAnalyzerResponse} object containing the analysis results.
   *          If the mode is unrecognized, a predefined empty response is returned.
   */
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

  private initializeCountMap(chars: Set<string>): Map<string, number> {
    const countMap = new Map<string, number>();
    for (const char of chars) {
      countMap.set(char, 0);
    }
    return countMap;
  }

  private countCharacters(
    input: string,
    filterFn: (char: string) => boolean,
    prefillMap?: Set<string>
  ): Map<string, number> {
    if (!input || input.trim() === '') {
      return new Map();
    }

    const countMap = prefillMap
      ? this.initializeCountMap(prefillMap)
      : new Map<string, number>();

    for (const char of input.toUpperCase()) {
      if (filterFn(char)) {
        countMap.set(char, (countMap.get(char) || 0) + 1);
      }
    }

    return countMap;
  }

  private analyzeTextForVowels(input: string): Map<string, number> {
    return this.countCharacters(input, this.isVowel.bind(this), this.VOWEL_SET);
  }

  private analyzeTextForConsonants(input: string): Map<string, number> {
    const LETTER_EXPRESSION: RegExp = /[A-Z]/;

    return this.countCharacters(
      input,
      (char) => !this.isVowel(char) && LETTER_EXPRESSION.test(char)
    );
  }

  private isVowel(char: string): boolean {
    return this.VOWEL_SET.has(char.toUpperCase());
  }
}
