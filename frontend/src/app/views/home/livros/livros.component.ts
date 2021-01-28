import { Component, OnInit } from '@angular/core';
import { Livro } from 'src/app/shared/model/livro.model';

@Component({
  selector: 'app-livros',
  templateUrl: './livros.component.html',
  styleUrls: ['./livros.component.css']
})
export class LivrosComponent implements OnInit {
  
  livros: Livro[];

  constructor() { }

  ngOnInit(): void {
  }

}
