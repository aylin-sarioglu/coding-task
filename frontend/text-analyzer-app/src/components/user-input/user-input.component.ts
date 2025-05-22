import { Component, model, output, signal } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {
  MatButtonToggleChange,
  MatButtonToggleModule,
} from '@angular/material/button-toggle';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { AnalyzerMode } from '../../services/text-analyzer';

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
  analyzerMode = model<AnalyzerMode>('VOWELS');
  isOnline = model<boolean>(false);
  onAnalyze = output<string>();

  hideSingleSelectionIndicator = signal(false);

  toggleSingleSelectionIndicator() {
    this.hideSingleSelectionIndicator.update((value) => !value);
  }

  onSelectionChange(event: MatButtonToggleChange) {
    this.analyzerMode.set(event.value);
  }

  onSubmit(): void {
    this.onAnalyze.emit(this.inputText);
  }
}
