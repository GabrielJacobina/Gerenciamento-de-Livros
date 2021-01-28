import { Livro } from './../model/livro.model';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { ResponsePageable } from '../model/responsePageable.model';

@Injectable({
  providedIn: 'root'
})
export class LivroService {
  livro: Livro;

  apiUrl = 'http://localhost:8080/livros'

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(
    private httpClient: HttpClient
  ) { }

  public getLivros(): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(this.apiUrl, this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      );
  }

  public postLivros(livro: Livro): Observable<Livro> {
    return this.httpClient.post<Livro>(this.apiUrl, livro, this.httpOptions)
    .pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  public putLivros(livro: Livro): Observable<Livro> {
    return this.httpClient.put<Livro>(`${this.apiUrl}/${livro.id}`, livro, this.httpOptions)
    .pipe(
      retry(2),
      catchError(this.handleError)
    );
  }


  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Erro ocorreu no lado do client
      errorMessage = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  };

}
