import { Component } from '@angular/core';
import { AnalysisResultComponent } from '../components/analysis-result/analysis-result.component';
import { UserInputComponent } from '../components/user-input/user-input.component';

@Component({
  selector: 'app-root',
  imports: [AnalysisResultComponent, UserInputComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'text-analyzer-app';
}
