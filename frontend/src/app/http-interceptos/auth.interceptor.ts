import { JwtService } from './../shared/service/jwt.service';

import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest }  from '@angular/common/http'
import { throwError } from 'rxjs';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor (
        private jwtService: JwtService
    ) {  }

    intercept(req: HttpRequest<any>, next: HttpHandler) {
        const token =  this.jwtService.getAuthorizationToken();
        let request: HttpRequest<any> = req;

        if (token)  {
            request=req.clone({
                headers: req.headers.set('Authorization', `Bearer  ${token}`)
            })
        }
        return next.handle(request)
            .pipe(
                catchError(this.handleError)
            )
    }

    private handleError(error: HttpErrorResponse) {
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