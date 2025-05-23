import { OfflineTextAnalyzer } from './offline-text-analyzer';
import { TextAnalyzerRequest, TextAnalyzerResponse } from './text-analyzer';

/**  Example tests: 'force' is only used to allow execution with 'ng test' without errors,
since the other test files are not yet implemented.*/

fdescribe('OfflineTextAnalyzer', () => {
  let service: OfflineTextAnalyzer;

  beforeEach(() => {
    service = new OfflineTextAnalyzer();
  });

  fit('should be created', () => {
    expect(service).toBeTruthy();
  });

  fit('should analyze vowels correctly', async () => {
    const request: TextAnalyzerRequest = {
      inputText: 'Hello World!',
      analyzerMode: 'VOWELS',
    };

    const response: TextAnalyzerResponse = await service.analyze(request);

    expect(response.inputText).toBe('Hello World!');
    expect(response.analyzerMode).toBe('VOWELS');
    expect(response.isOffline).toBeTrue();

    const report = response.report;

    expect(report.get('A')).toBe(0);
    expect(report.get('E')).toBe(1);
    expect(report.get('I')).toBe(0);
    expect(report.get('O')).toBe(2);
    expect(report.get('U')).toBe(0);
  });

  fit('should analyze consonants correctly', async () => {
    const request: TextAnalyzerRequest = {
      inputText: 'Test',
      analyzerMode: 'CONSONANTS',
    };

    const response: TextAnalyzerResponse = await service.analyze(request);

    expect(response.inputText).toBe('Test');
    expect(response.analyzerMode).toBe('CONSONANTS');
    expect(response.isOffline).toBeTrue();

    const report = response.report;

    expect(report.get('T')).toBe(2);
    expect(report.get('S')).toBe(1);
    expect(report.get('E')).toBeUndefined(); // vowel should not be counted
  });

  fit('should return an empty map when input is only whitespace (VOWELS)', async () => {
    const request: TextAnalyzerRequest = {
      inputText: '    ',
      analyzerMode: 'VOWELS',
    };

    const response = await service.analyze(request);
    expect(response.report.size).toBe(0);
  });

  fit('should return an empty map when input is empty (CONSONANTS)', async () => {
    const request: TextAnalyzerRequest = {
      inputText: '',
      analyzerMode: 'CONSONANTS',
    };

    const response = await service.analyze(request);
    expect(response.report.size).toBe(0);
  });
});
