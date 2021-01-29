# Gerenciamento de livros

# Passos para rodar o projeto

1. Tenha o [Java 11](https://www.java.com/download/), [Maven](https://maven.apache.org), [Npm](https://www.npmjs.com/), [AngularCLI](https://cli.angular.io/) e o [Docker](https://www.docker.com/) instalados

2. Clone este repositório
  
```
$ git clone https://github.com/GabrielJacobina/Gerenciamento-de-Livros.git
```

3. Entre na pasta do backend 

```
$ cd gerenciamento-de-livros and cd backend
```

4. Instale as dependências

```
$ mvn install
```

5. Inicie o container mysql
```
$ docker-compose up
```

6. Rode o backend do projeto

```
$ mvn spring-boot:run
```

7. Abra a pasta do frontend

```
cd ../frontend
```

8. Instale as dependências
```
npm i
```

9. Rode o frontend
```
ng serve -o
```