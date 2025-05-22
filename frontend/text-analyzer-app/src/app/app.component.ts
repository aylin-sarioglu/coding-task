import { Component } from '@angular/core';
import { AnalyzerResultComponent } from '../components/analyzer-result/analyzer-result.component';
import { UserInputComponent } from '../components/user-input/user-input.component';

@Component({
  selector: 'app-root',
  imports: [AnalyzerResultComponent, UserInputComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'text-analyzer-app';
}
