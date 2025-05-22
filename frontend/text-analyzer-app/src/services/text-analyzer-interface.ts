import { Observable } from 'rxjs';
export type AnalyzerMode = 'VOWELS' | 'CONSONANTS';

export interface TextAnalyzerRequest {
  inputText: string;
  analyzerMode: AnalyzerMode;
}

export interface TextAnalyzerResponse {
  inputText: string;
  analyzerMode: AnalyzerMode;
  report: Record<string, number>;
}

export interface TextAnalyzer {
  analyze(data: TextAnalyzerRequest): Observable<TextAnalyzerResponse>;
}
