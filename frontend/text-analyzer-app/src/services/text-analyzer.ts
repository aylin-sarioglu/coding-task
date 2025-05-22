import { AnalyzerMode } from './text-analyzer-interface';

export class TextAnalyzer {
  private static readonly VOWEL_SET: Set<string> = new Set([
    'A',
    'E',
    'I',
    'O',
    'U',
  ]);

  public static analyze(
    input: string,
    type: AnalyzerMode
  ): Map<string, number> {
    switch (type) {
      case 'VOWELS':
        return this.analyzeTextForVowels(input);
      case 'CONSONANTS':
        return this.analyzeTextForConsonants(input);
      default:
        return this.analyzeTextForVowels(input);
    }
  }

  private static analyzeTextForVowels(input: string): Map<string, number> {
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

  private static analyzeTextForConsonants(input: string): Map<string, number> {
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

  private static isVowel(char: string): boolean {
    return this.VOWEL_SET.has(char.toUpperCase());
  }
}
