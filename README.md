# 🛒 Gerenciador de compras
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/Rayllanderson/gamelist/blob/master/LICENSE) 

## 💻 Sobre o projeto

https://gerenciador-de-compras-rayllanderson.netlify.app

Versão 3.0 is out!!!

Gerenciador de compras é uma aplicação web que consiste na organização de suas listas de compras. A Aplicação tem o foco de organizar seus gastos de acordo com cada lista para dar lhe dar mais controle e evitar fica calculando diversas vezes. Basta adicionar os produtos em sua lista e então a aplicação vai guardar os valores, gerar informações úteis, como, também, gráficos baseados nessas listas.
Gerenciador de compras é uma aplicação desenvolvida para uso pessoal.

## 🎨 Web

### Tela de listas
<img src="https://user-images.githubusercontent.com/63964369/118383287-dff3f980-b5d2-11eb-82af-f343b76dda86.png" width="90%" height="90%" >


### Tela de produtos
<img src="https://user-images.githubusercontent.com/63964369/118383262-b33fe200-b5d2-11eb-9a53-e9bfdeed4057.png" width="90%" height="90%" >


### Estatísticas
<img src="https://user-images.githubusercontent.com/63964369/118383300-ff8b2200-b5d2-11eb-9ccf-f15e631b2169.png" width="90%" height="90%" >


## 📱 Mobile
![mobile](https://user-images.githubusercontent.com/63964369/118383325-23e6fe80-b5d3-11eb-8555-ae0adef0bc2a.png)


## 📹 Demonstração 

https://user-images.githubusercontent.com/63964369/118383212-3ca2e480-b5d2-11eb-8e1c-69030c48d32f.mp4


## 🛠 Tecnologias utilizadas
### :coffee: Back end
- [Java](https://www.oracle.com/br/java/)
- JPA / Hibernate
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Framework](https://spring.io/projects/spring-framework)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) 
- [Spring Security](https://spring.io/projects/spring-security)
- [Maven](https://maven.apache.org/)

### ⚛️ Front end
- [Typescript](https://www.typescriptlang.org/)
- [React](https://pt-br.reactjs.org/)
- CSS
- HTML

## :hammer: Implantação em produção
- Back end: Heroku
- Front end web: Netlify
- Banco de dados: Postgresql

## 🚀 Como executar o projeto

### 🎲 Back end

Pré-requisitos: Java 11

```bash
# clonar repositório
git clone https://github.com/Rayllanderson/gerenciador-de-compras.git

# entrar na pasta da versão 3.0 do projeto
cd v3.0

# entrar na pasta do projeto api
cd api

# executar o projeto
./mvnw spring-boot:run
```

### 🧭 Front end web

Pré-requisitos: npm / yarn

💡 O Front End precisa que o Back End esteja sendo executado para funcionar.

💡 Trocar a url da api em `front-web/src/services/api.ts` de `process.env.REACT_APP_API_URL` para `http://localhost:8080/api/v1` ou criar um arquivo

```bash
# clonar o repositório. pule essa etapa caso já tenha clonado anteriormente
git clone https://github.com/Rayllanderson/gerenciador-de-compras.git

# entrar na pasta da versão 3.0 do projeto
cd v3.0

# entrar na pasta front-web
cd front-web

# instalar dependências
yarn install

# executar o projeto
yarn start
```

## ❓ Como contribuir para o projeto

1. Faça um **fork** do projeto.
2. Crie uma nova branch com as suas alterações: `git checkout -b my-feature`
3. Salve as alterações e crie uma mensagem de commit contando o que você fez: `git commit -m "feature: My new feature"`
4. Envie as suas alterações: `git push origin my-feature`
> Caso tenha alguma dúvida confira este [guia de como contribuir no GitHub](https://github.com/firstcontributions/first-contributions)

## 👓 Observações
Esta é uma versão 3.0. Para conferir as versões anteriores, siga os links abaixo:
 - [Versão 1.0](https://github.com/Rayllanderson/gerenciador-compras/tree/master/v1.0)
 - [Versão 2.0](https://github.com/Rayllanderson/gerenciador-compras/tree/master/v2.0)

## 📝 Licença

Este projeto esta sobe a licença MIT.

Rayllanderson Gonçalves Rodrigues

https://www.linkedin.com/in/rayllanderson/
