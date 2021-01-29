import { JwtService } from './../../shared/service/jwt.service';
import { Usuario } from './../../shared/model/usuario.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {
  hide = true;
  public usuarioForm: FormGroup;
  usuario: Usuario;

  constructor(
    private jwtService: JwtService,
    private fb: FormBuilder,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.usuarioForm = new FormGroup({
      nome: new FormControl(),
      username: new FormControl(),
      password: new FormControl(),
    })
  }

  register() {
    this.jwtService.register(this.usuarioForm.value).subscribe(result => {
      this.router.navigate(['']);
    });
    this.usuarioForm.reset();
  }

}
