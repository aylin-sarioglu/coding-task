import { Component, input, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {
  MatButtonToggleChange,
  MatButtonToggleModule,
} from '@angular/material/button-toggle';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import {
  AnalyzerMode,
  TextAnalyzer,
  TextAnalyzerRequest,
  TextAnalyzerResponse,
} from '../../services/text-analyzer-interface';
import { OnlineTextAnalyzer } from '../../services/online-text-analyzer';

@Component({
  selector: 'app-user-input',
  imports: [
    MatSlideToggleModule,
    MatLabel,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSlideToggleModule,
    MatButtonToggleModule,
    MatCheckboxModule,
    FormsModule,
  ],
  templateUrl: './user-input.component.html',
  styleUrl: './user-input.component.css',
})
export class UserInputComponent {
  inputText: string = '';
  selectedValue: AnalyzerMode = 'VOWELS';
  isOnline: boolean = false;

  constructor(private readonly textAnalyzer: OnlineTextAnalyzer) {}

  hideSingleSelectionIndicator = signal(false);

  toggleSingleSelectionIndicator() {
    this.hideSingleSelectionIndicator.update((value) => !value);
  }

  onSelectionChange(event: MatButtonToggleChange) {
    this.selectedValue = event.value;
  }

  onSubmit(): void {
    const request: TextAnalyzerRequest = {
      inputText: this.inputText,
      analyzerMode: this.selectedValue,
    };

    this.textAnalyzer.analyze(request).subscribe({
      next: (response: TextAnalyzerResponse) => {
        console.log('Analyzed following request: ', request);
      },
      error: (err) => {
        console.error(
          'An error occurred while analyzing the text. Please try again.',
          err
        );
      },
    });
  }
}
