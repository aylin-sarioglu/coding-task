import { HttpClient } from '@angular/common/http';
import {
  TextAnalyzer,
  TextAnalyzerRequest,
  TextAnalyzerResponse,
} from './text-analyzer-interface';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environments';

@Injectable({
  providedIn: 'root',
})
export class OnlineTextAnalyzer implements TextAnalyzer {
  private readonly apiUrl = `${environment.apiBaseUrl}/api/analyze`;

  constructor(private readonly http: HttpClient) {}

  // allow  using response in multiple components
  private resultSubject = new BehaviorSubject<TextAnalyzerResponse | null>(null);
  result$ = this.resultSubject.asObservable(); 


  analyze(data: TextAnalyzerRequest): Observable<TextAnalyzerResponse> {
    return this.http.post<TextAnalyzerResponse>(this.apiUrl, data).pipe(
       tap(result => this.resultSubject.next(result))
    );
  }
}
