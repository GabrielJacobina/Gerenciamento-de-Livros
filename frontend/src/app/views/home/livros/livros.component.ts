import { LivroService } from './../../../shared/service/livro.service';
import { Component, OnInit } from '@angular/core';
import { Livro } from 'src/app/shared/model/livro.model';

@Component({
  selector: 'app-livros',
  templateUrl: './livros.component.html',
  styleUrls: ['./livros.component.css']
})
export class LivrosComponent implements OnInit {
  
  livros: Livro[];

  constructor(
    public livroService: LivroService
  ) { }

  ngOnInit(): void {
    this.getLivros();
  }

  getLivros() {
    this.livroService.getLivros().subscribe(data => {
      this.livros = data.content;
      console.log(this.livros)
    })
  }

}
