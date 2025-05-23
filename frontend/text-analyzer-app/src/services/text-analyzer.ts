export type AnalyzerMode = 'VOWELS' | 'CONSONANTS';

export interface TextAnalyzerRequest {
  inputText: string;
  analyzerMode: AnalyzerMode;
}

export interface TextAnalyzerResponse {
  inputText: string;
  analyzerMode: AnalyzerMode;
  report: Map<string, number>;
  isOffline?: boolean;
}

export interface TextAnalyzer {
  analyze(data: TextAnalyzerRequest): Promise<TextAnalyzerResponse>;
}
