import { retry, catchError } from 'rxjs/operators';
import { Usuario } from './../model/usuario.model';
import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class JwtService {
  jwtPayload: any;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(
    private httpClient: HttpClient,
    private jwtHelper: JwtHelperService,
  ) { }

  async login(usuario: Usuario) {
    return this.httpClient.post<any>(`${environment.apiUrl}/auth/login`, usuario, this.httpOptions)
      .toPromise()
      .then(res => {
        console.log("print")
        this.armazenarToken(res.token)
        console.log("Alguma coisa" + res.token)
      })
      .catch(response => {
        console.log(response)
      })
  }

  register(usuario: Usuario) {
    return this.httpClient.post<{ access_token: string }>(`${environment.apiUrl}/auth/register`, usuario, this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  getAuthorizationToken()  {
    const token = window.localStorage.getItem('access_token');
    return token;
  }

  isUserLoggedIn() {
    const token = this.getAuthorizationToken();
    if (!token) {
      return false;
    }
    return true;
  }

  private armazenarToken(access_token: string) {
    this.jwtPayload = this.jwtHelper.decodeToken(access_token);
    window.localStorage.setItem('access_token', access_token);
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
