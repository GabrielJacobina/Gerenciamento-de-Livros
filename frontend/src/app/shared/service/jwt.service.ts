import { Usuario } from './../model/usuario.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor(
    private httpClient: HttpClient
  ) { }

  login(username: string, password: string) {
    return this.httpClient.post<{ access_token: string }>(`${environment.apiUrl}/auth/login`, { username, password })
      .pipe(tap(res => {
        localStorage.setItem('access_token', res.access_token);
      }))
  }

  register(usuario: Usuario) {
    return this.httpClient.post<{ access_token: string }>(`${environment.apiUrl}/auth/register`, usuario).pipe(tap(res => {
      this.login(usuario.username, usuario.password)
    }))
  }

  logout() {
    localStorage.removeItem('access_token');
  }
}
