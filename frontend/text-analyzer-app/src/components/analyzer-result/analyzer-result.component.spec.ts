import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyzerResultComponent } from './analyzer-result.component';
import { MatChipsModule } from '@angular/material/chips';

describe('AnalysisResultComponent', () => {
  let component: AnalyzerResultComponent;
  let fixture: ComponentFixture<AnalyzerResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnalyzerResultComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnalyzerResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
