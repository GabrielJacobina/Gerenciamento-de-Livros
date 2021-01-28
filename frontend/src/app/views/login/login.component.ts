import { Router } from '@angular/router';
import { JwtService } from './../../shared/service/jwt.service';
import { Usuario } from './../../shared/model/usuario.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  hide = true;

  usuario: Usuario = new Usuario();

  constructor(
    private jwtService: JwtService,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  login(username: string, password: string) {
    this.jwtService.login(username, password)
      .then(() => { this.router.navigate([''])
    });
  }

}
