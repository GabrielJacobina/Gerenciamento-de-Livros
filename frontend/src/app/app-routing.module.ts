import { CreateLivroComponent } from './views/home/livros/create-livro/create-livro.component';
import { AuthGuard } from './guards/auth.guard';
import { CreateAccountComponent } from './views/create-account/create-account.component';
import { LoginComponent } from './views/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LivrosComponent } from './views/home/livros/livros.component';

const routes: Routes = [
  {
    path: '', component: LivrosComponent,
      canActivate: [AuthGuard]
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'create-account', component: CreateAccountComponent
  },
  {
    path: 'create-livro', component: CreateLivroComponent,
      canActivate: [AuthGuard]
  },
  {
    path: 'create-livro/:id', component: CreateLivroComponent,
      canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
