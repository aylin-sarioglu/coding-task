import { HttpClient } from '@angular/common/http';
import {
  TextAnalyzer,
  TextAnalyzerRequest,
  TextAnalyzerResponse,
} from './text-analyzer-interface';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environments';

@Injectable({
  providedIn: 'root',
})
export class OnlineTextAnalyzer implements TextAnalyzer {
  private readonly apiUrl = `${environment.apiBaseUrl}/api/analyze`;

  constructor(private readonly http: HttpClient) {}

  analyze(data: TextAnalyzerRequest): Observable<TextAnalyzerResponse> {
    return this.http.post<TextAnalyzerResponse>(this.apiUrl, data);
  }
}
