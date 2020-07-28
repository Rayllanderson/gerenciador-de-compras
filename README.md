# Gerenciador-compras-JDBC
<strong>Um gerenciador de listas contendo produtos utilziando Banco de dados com JDBC
<br></strong>Apenas um projeto pessoal com intuíto de treinar o que eu aprendi até o momento. Infelizmente não tenho conhecimento suficiente sobre hmtl, css e javascript pra ter uma interface amigável, então decidi fazer no terminal, já que é algo pra uso pessoal. Pensei em fazer um aplicativo mobile ou utilizar javafx/swing, no entanto, meu conhecimento sobre ainda é muito limitado. <br>Pretendo aprimorar mais assim que aprender novas técnologias. Meu próximo objetivo para esse projeto é ultilziar spring boot e jpa/hibernate com um pouco de html, css e javascript. Mas, atualmente, é a única interface que consegui fazer.<br>
<br><strong>O que esse programa faz? <br></strong> <strong>R:</strong> É um gerenciador de listas de compra! Você adiciona os produtos, o preço, e, se preferir, também pode adicionar um orçamento para a lista. Então, você tem acesso a funções úteis. Também pode editar produto, editar lista, excluir lista, excluir produto, adicionar quantas listas quiser, quantos produtos quiser em uma lista.
<br><strong>O que isso tem de inovador?<br></strong> <strong>R: </strong> Absolutamente nada! Eu fiz apenas para me divetir, pra treinar e era algo que queria fazer pra ajudar a organizar a compra do meu computador que estou montando.
<br><strong>Como funciona?<br></strong> <strong>R: </strong>O Programa inicia na sua tela de login, contendo duas opçõs possíveis, Login ou Cadastrar. Em cadastro, você não pode usar um username já existente. Em login, você apenas loga com sua conta.
![Tela Inicial](https://i.imgur.com/RZ29cih.png)

Após você logar, você tem acesso as funções iniciais, Acessar suas listas, Criar uma nova lista, editar, excluir e configurações de conta:

![Tela Inicial Listas](https://i.imgur.com/tf5hdVm.png)

Clicando em acessar suas listas, listará todas as suas listas criadas e você escolherá qual delas quer acessar

![Tela Inicial Listas](https://i.imgur.com/YhpVlDY.png)

Então, após escolher sua lista, terá acesso aos seus produtos criados, podendo adicionar, editar, excluir e adicionar ou editar o orçamento pra atual lista 

<strong>Algumas explicações importantes: </strong> <br>
Valor estipulado - um valor que você acha que vai pagar em tal produto.<br>
Valor real - Valor que você realmente pagou. Ou seja, (valor estipulado - valor real) = valor economizado <br>
E o Orçamento? O orçamento é apenas uma base pra você ir se guiando. Como por exemplo, nesta pergunta: "Quanto eu tenho disponível para gastar?" para responder isso, você precisa ter uma noção de quanto tem pra se guiar e então poder realizar os cálculos. 

![Tela Inicial Listas](https://i.imgur.com/EEg96iN.png)

Clicando na primeira opção, há algumas funções que eu queria implementar, algumas funções úteis para facilitar

![Menu funcoes uteis](https://i.imgur.com/4PtbY2n.png)

Veja alguns exemplos das funções:

Listando produtos comprados<br>
![Listando produtos comprados](https://i.imgur.com/pEroSJQ.png)

Listando produtos não comprados e valor disponível para compra de acordo com o orçamento para lista
![Produtos n comprados e valor disponivel](https://i.imgur.com/Y6lxHMO.png) 

No menu de editar produtos, você pode alterar tudo! Preço estipulado, preço que você pagou (caso você já tenha comprado ou caso queira simular um valor), nome, marcar como comprado, marcar como não comprado e mover o produto escolhido para outra lista

![menuEditar produto](https://i.imgur.com/0NbOry1.png)

E, por fim, a configuação da conta, que você tem acesso a todas ao número total de listas que possui, valor total gasto (soma dos valores reais de todas as listas), valor total estipulado, ou seja, básicamente é uma soma geral de todas as listas que possui. Além disso, claro, pode alterar seu user, senha ou nome.

![config conta](https://i.imgur.com/yEQMa2B.png)

Confira outras imagens do programa:<br>
![img1](https://i.imgur.com/0ss1yUj.png)
![img2](https://i.imgur.com/j1185V8.png)
![img3](https://i.imgur.com/nJyAPBt.png)
![img4](https://i.imgur.com/87feWvd.png)
![img5](https://i.imgur.com/ZOwXRtx.png)
![img6](https://i.imgur.com/CLxXG50.png)<br>
<strong>Acho válido colocar novamente as explicações pra caso alguém não tenha lido</strong> <br>
Valor estipulado - um valor que você acha que vai pagar em tal produto.<br>
Valor real - Valor que você realmente pagou. Ou seja, (valor estipulado - valor real) = valor economizado <br>
E o Orçamento? O orçamento é apenas uma base pra você ir se guiando. Como por exemplo, nesta pergunta: "Quanto eu tenho disponível para gastar?" para responder isso, você precisa ter uma noção de quanto tem pra se guiar e então poder realizar os cálculos. <br><br><br>


BONUS: ~(forget that)~<br>
![imgBn](https://i.imgur.com/9Cx2z8d.jpg)
