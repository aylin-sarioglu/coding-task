import { Component, input } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { TextAnalyzerResponse } from '../../services/text-analyzer';
import { CommonModule } from '@angular/common';
import { MatChipsModule } from '@angular/material/chips';

@Component({
  selector: 'app-analyzer-result',
  imports: [MatListModule, CommonModule, MatChipsModule],
  templateUrl: './analyzer-result.component.html',
  styleUrl: './analyzer-result.component.css',
})
export class AnalyzerResultComponent {
  results = input<TextAnalyzerResponse[]>([]);
}
