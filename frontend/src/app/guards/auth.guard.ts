import { JwtService } from './../shared/service/jwt.service';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private jwtService: JwtService,
    private router: Router
  ) { }
  canActivate(route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean | Observable<boolean> {
      if(this.jwtService.isUserLoggedIn()){
        return true;
      } 
      this.router.navigate(['/login']);
    
      return false;
  }
  
}
