import { Component, input } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { TextAnalyzerResponse } from '../../services/text-analyzer';
import { CommonModule } from '@angular/common';
import { MatChipsModule } from '@angular/material/chips';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-analyzer-result',
  imports: [MatListModule, CommonModule, MatChipsModule, MatCardModule],
  templateUrl: './analyzer-result.component.html',
  styleUrl: './analyzer-result.component.css',
})
export class AnalyzerResultComponent {
  results = input<TextAnalyzerResponse[]>([]);
}
