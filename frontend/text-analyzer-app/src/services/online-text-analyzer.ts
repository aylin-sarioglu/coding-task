import { HttpClient } from '@angular/common/http';
import {
  TextAnalyzer,
  TextAnalyzerRequest,
  TextAnalyzerResponse,
} from './text-analyzer';
import { lastValueFrom, take } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environments';

@Injectable({
  providedIn: 'root',
})
export class OnlineTextAnalyzer implements TextAnalyzer {
  private readonly apiUrl = `${environment.apiBaseUrl}/api/analyze`;

  constructor(private readonly http: HttpClient) {}

  /**
   * Sends a text analysis request to the backend API.
   *
   * This method posts the provided {@link TextAnalyzerRequest} to the configured API endpoint
   * and returns a promise that resolves to a {@link TextAnalyzerResponse}.
   *
   * @param data The request payload containing the text and the selected analysis mode.
   * @returns A promise resolving to the text analysis response from the API.
   */
  analyze(data: TextAnalyzerRequest): Promise<TextAnalyzerResponse> {
    // for easiness
    const request$ = this.http
      .post<TextAnalyzerResponse>(this.apiUrl, data)
      .pipe(take(1));
    return lastValueFrom(request$);
  }
}
