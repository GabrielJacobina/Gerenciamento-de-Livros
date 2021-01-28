import { Usuario } from "./usuario.model";

export class Livro{
    id: number;
    nome: string;
    dataDeCadastro: string;
    idUsuario: Usuario;
}