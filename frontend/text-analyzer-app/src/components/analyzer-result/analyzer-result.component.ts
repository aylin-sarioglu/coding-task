import { Component } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import {
  AnalyzerMode,
  TextAnalyzerResponse,
} from '../../services/text-analyzer-interface';
import { OnlineTextAnalyzer } from '../../services/online-text-analyzer';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-analyzer-result',
  imports: [MatListModule, CommonModule],
  templateUrl: './analyzer-result.component.html',
  styleUrl: './analyzer-result.component.css',
})
export class AnalyzerResultComponent {
  result: TextAnalyzerResponse | null = null;

  constructor(private readonly textAnalyzer: OnlineTextAnalyzer) {}

  ngOnInit(): void {
    this.textAnalyzer.result$.subscribe((response) => {
      this.result = response;
    });
  }
}
