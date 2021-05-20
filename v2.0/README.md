# Gerenciador de compras

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/Rayllanderson/gerenciador-compras/blob/master/LICENSE)

# Sobre o projeto

http://lardopresenteperfeito.site:8080/gerenciador-compras/

Gerenciador de compras é uma aplicação web desenvolvida para uso pessoal, com o intuito de pôr em prática tudo aprendido
até o momento.

A aplicação consiste em uma lista de compras, em que um usuário pode ter sua própria lista pessoal. Dentro delas, podem
conter um ou mais produtos. Os produtos serão listados em uma tabela, e, através dos preços desses produtos, é possível
gerar informações úteis, como, também, gerar gráficos com base nesses dados.

## Layout web

<h3> Home </h3>

<img src="https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/home.png" width="90%" height="90%" >

<h3> Tela de listas </h3>

<img src="https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/listas.png" width="90%" height="90%" >

<h3> Tela de produtos </h3>

<img src="https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/produtos.png" width="90%" height="90%" >

<h3> Estatísticas </h3>

<img src="https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/estatisticas_1.png" width="90%" height="90%" >

## Mobile

<h3> Tela de Account e Estatísticas</h3>

<img src="https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/account_m.jpeg" width="48%"/>  <img src="https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/estatistica_m.jpeg" width="48%"/>

<h3> Informações dos produtos e editando um produto </h3> 

<img src="https://github.com/Rayllanderson/assets/raw/master/gerenciador-compras/prod_m.jpeg" width="48%"/> <img src="https://github.com/Rayllanderson/assets/raw/master/gerenciador-compras/edit_m.jpeg" width="48%"/>

## Modelo conceitual

![Modelo Conceitual](https://github.com/Rayllanderson/assets/raw/master/gerenciador-compras/modelo_conceitual.jpeg)

# Tecnologias utilizadas

## Back end

- Java
- JDBC
- Servlet

## Front end

- JSP / HTML
- JQuery / JS
- Bootstrap / CSS
- Chart.js
- Bootstrap Table

## Implantação em produção

- Amazon AWS
- Banco de dados: MySQL

# Observações

Esta é uma versão 2.0. Para consultar a versão
1.0, <a href="https://github.com/Rayllanderson/gerenciador-compras/tree/master/v1.0"> clique aqui </a>

Sobre o frontend: O foco dessa versão foi implementar uma interface gráfica. Optei por usar jsp, html, css e javascript.
Importante ressaltar que meus conhecimentos sobre a parte frontend são bem básicos e muito limitados, possivelmente
possui muitos erros e desorganização, que, se eu tivesse me dedicado para estudar um pouco mais sobre frontend, eu
saberia trabalhar melhor.

Sobre o backend: Como dito acima, o foco foi a interface. Portanto, não alterei muito do backend em questões de
implementações, organizações, novas frameworks ou coisas do tipo. Apenas acoplei a servlet, implementei poucas funções
novas e organizei minimamente. Em uma próxima versão, pretendo fazer um refactor geral do backend.

# Autor

Rayllanderson Gonçalves Rodrigues
