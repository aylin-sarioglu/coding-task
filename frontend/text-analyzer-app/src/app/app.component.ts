import { Component, signal } from '@angular/core';
import { AnalyzerResultComponent } from '../components/analyzer-result/analyzer-result.component';
import { UserInputComponent } from '../components/user-input/user-input.component';
import { AnalyzerMode, TextAnalyzer, TextAnalyzerRequest, TextAnalyzerResponse } from '../services/text-analyzer-interface';
import { OnlineTextAnalyzer } from '../services/online-text-analyzer';
import { OfflineTextAnalyzer } from '../services/offline-text-analyzer';


@Component({
  selector: 'app-root',
  imports: [AnalyzerResultComponent, UserInputComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'text-analyzer-app';

  reports = signal<TextAnalyzerResponse[]>([])
  analyzerMode = signal<AnalyzerMode>('VOWELS');
  isOnline = signal<boolean>(false);


  constructor(
    private readonly onlineTextAnalyzer: OnlineTextAnalyzer,
    private readonly offlineTextAnalyzer: OfflineTextAnalyzer
  ) {}

  get textAnalyzer(): TextAnalyzer {
    if (this.isOnline()) {
      return this.onlineTextAnalyzer;
    }
    return this.offlineTextAnalyzer;
  }

  async onAnalyze(input: string) {
    const request: TextAnalyzerRequest = {
      inputText: input,
      analyzerMode: this.analyzerMode(),
    };

    const response = await this.textAnalyzer.analyze(request);
    this.reports.update(reports => [response, ...reports])
  } 
}