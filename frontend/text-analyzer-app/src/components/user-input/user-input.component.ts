import { Component, signal } from '@angular/core';
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
  isOnline = false;

  hideMultipleSelectionIndicator = signal(false);

  toggleMultipleSelectionIndicator() {
    this.hideMultipleSelectionIndicator.update((value) => !value);
  }

  selectedValues: string[] = ['Vowels'];
  onSelectionChange(event: MatButtonToggleChange) {
    enforceMinimumModeSelection(event, this.selectedValues);
  }
}

function enforceMinimumModeSelection(
  event: MatButtonToggleChange,
  selectedValues: string[]
) {
  const newSelection = event.source.buttonToggleGroup.value;

  if (!newSelection || newSelection.length === 0) {
    event.source.buttonToggleGroup.value = selectedValues;
    return;
  }

  selectedValues = newSelection;
}
