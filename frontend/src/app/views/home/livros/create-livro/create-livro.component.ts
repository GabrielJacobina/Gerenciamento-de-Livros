import { ActivatedRoute, Router } from '@angular/router';
import { Livro } from './../../../../shared/model/livro.model';
import { LivroService } from './../../../../shared/service/livro.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-livro',
  templateUrl: './create-livro.component.html',
  styleUrls: ['./create-livro.component.css']
})
export class CreateLivroComponent implements OnInit {
  livro: Livro = new Livro();
  att = false;

  constructor(
    private livroService: LivroService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id == null || id == undefined || id == 0) {
      
    } else {
      this.livroService.getLivroById(id).subscribe(livro => {
        this.livro = livro;
        this.att = true
      })
    }
  }

  register() {
    this.livroService.postLivros(this.livro).subscribe(result => {
      this.router.navigate(['/create-livro']);
    });
  }

  updateLivro() {
    this.livroService.putLivros(this.livro).subscribe(() => {
      this.router.navigate(['']);
    })
  }

  cancel() {
    this.router.navigate(['']);
  }

}
