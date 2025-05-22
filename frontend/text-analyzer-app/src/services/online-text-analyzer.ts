import { HttpClient } from '@angular/common/http';
import {
  TextAnalyzer,
  TextAnalyzerRequest,
  TextAnalyzerResponse,
} from './text-analyzer-interface';
import { BehaviorSubject, lastValueFrom, take } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environments';

@Injectable({
  providedIn: 'root',
})
export class OnlineTextAnalyzer implements TextAnalyzer {
  private readonly apiUrl = `${environment.apiBaseUrl}/api/analyze`;

  constructor(private readonly http: HttpClient) {}

  analyze(data: TextAnalyzerRequest): Promise<TextAnalyzerResponse> {
    // for easiness
    const request$ = this.http
      .post<TextAnalyzerResponse>(this.apiUrl, data)
      .pipe(take(1));
    return lastValueFrom(request$);
  }
}
