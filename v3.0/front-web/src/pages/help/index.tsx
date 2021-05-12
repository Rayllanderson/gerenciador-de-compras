import {HelpCardContainer} from '../../components/Card/styles';
import {Title, SubTitle} from '../../components/Text/styles';
import {FiFacebook, FiGithub, FiHelpCircle, FiLinkedin} from "react-icons/all";
import {Summary, SummaryHeader, SummaryItem, HelpContent, HelpContentHeader, Image} from './styles';
import React from "react";

export function HelpPage() {
    return (
        <HelpCardContainer className={'container card mt-5'}>
            <Title className={'pt-4'}> <FiHelpCircle/> Ajuda </Title>
            <SubTitle className={'pt-5'}>Sumário</SubTitle>
            <Summary className={'pt-2'}>
                <SummaryHeader> Listas </SummaryHeader>
                <SummaryItem href={'#newList'}>Como adicionar uma nova lista?</SummaryItem>
                <SummaryItem href={'#progressBar'}>O que significa a barra que fica nas listas?</SummaryItem>
                <SummaryItem href={'#editList'}>Como edita uma lista?</SummaryItem>
                <SummaryItem href={'#removeList'}>Como apaga uma lista?</SummaryItem>
                <SummaryItem href={'#removeMultiplesLists'}>Como apaga várias lista de uma vez?</SummaryItem>
                <SummaryItem href={'#duplicateList'}>Duplicando Listas</SummaryItem>

                <SummaryHeader className={'pt-3'}> Produtos </SummaryHeader>
                <SummaryItem href={'#addProduct'}>Como eu adiciono um produto?</SummaryItem>
                <SummaryItem href={'#stipulatedPrice'}>O que significa Valor do produto?</SummaryItem>
                <SummaryItem href={'#spentPrice'}>O que significa valor gasto?</SummaryItem>
                <SummaryItem href={'#editProduct'}>Como editar um produto?</SummaryItem>
                <SummaryItem href={'#removeProduct'}>Como apaga um produto?</SummaryItem>
                <SummaryItem href={'#removeMultiplesProducts'}>Como apaga vários produtos de uma vez?</SummaryItem>
                <SummaryItem href={'#copyProducts'}>Como copiar produtos pra outra lista?</SummaryItem>
                <SummaryItem href={'#moveProducts'}>Como mover produtos pra outra lista?</SummaryItem>

                <SummaryHeader className={'pt-3'}>Card de Produtos</SummaryHeader>
                <SummaryItem href={'#cardCurrentPrice'}>O que significa Valor Atual?</SummaryItem>
                <SummaryItem href={'#cardStipulatedPrice'}>O que significa Valor Estipulado?</SummaryItem>

                <SummaryHeader className={'pt-3'}>Minha Conta</SummaryHeader>
                <SummaryItem href={'#changeProfileImage'}>Como colocar uma foto de perfil?</SummaryItem>
                <SummaryItem href={'#changeUsernameAndName'}>Como mudar Username ou nome?</SummaryItem>
                <SummaryItem href={'#changePassword'}>Como mudar a Senha?</SummaryItem>
            </Summary>
            <hr/>
            <HelpContent>
                <SubTitle className={'pt-3'}>Listas</SubTitle>
                <HelpContentHeader id={'newList'}> Como adicionar uma nova lista? </HelpContentHeader>
                <p> Para adicionar uma nova lista, basta clicar no símbolo de + (Mais) como o da imagem abaixo. </p>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>
                <p> Após clicar, irá surgir uma tela e apenas o nome é obrigatório. Orçamento pode ser qualquer valor
                    e totalmente opcional. No entanto, não terá como calcular o valor disponível
                    para gastar de uma lista sem orçamento.</p>

                <HelpContentHeader id={'progressBar'}>O que significa a barra que fica nas listas?</HelpContentHeader>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>
                <p>Significa o progresso de produtos comprados sua lista. Uma lista com progresso de 50%,
                    significa que metade dos produtos foram comprados, por exemplo.</p>

                <HelpContentHeader id={'editList'}>Como edita uma lista?</HelpContentHeader>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>
                <p>Após clicar nos 3 pontos (como o da imagem) basta selecionar <strong>Editar</strong>, e então editar a lista que deseja</p>

                <HelpContentHeader id={'removeList'}>Como apaga uma lista?</HelpContentHeader>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>
                <p>Após clicar nos 3 pontos (como o da imagem) basta selecionar <strong>Remover</strong>, e então editar a lista que deseja</p>

                <HelpContentHeader id={'removeMultiplesLists'}>Como apaga várias lista de uma vez?</HelpContentHeader>
                <p>Clicando nos <a href={'#options'}>3 pontinhos (como na imagem acima)</a> Selecione a opção <strong>Selecionar</strong>
                 agora é só selecionar as listas desejadas que deseja remover </p>
                <Image className={'img-fluid rounded '} id={'options'}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>

                <HelpContentHeader id={'duplicateList'}>Duplicando Listas</HelpContentHeader>
                <p>Nova funcionalidade exclusivamente da versão 3.0 xD. Para duplicar uma lista, basta seguir os mesmos passos
                <a href={'#removeMultiplesLists'}> acima</a>, porém, ao invés de escolher 'Remover', selecione <strong>Duplicar </strong></p>
                <p>Quando se duplica uma lista, todos os produtos dentro dela também serão duplicados mantendo seu estado original,
                e a nova lista terá o nome da original seguido de 'cópia' </p>
                <Image className={'img-fluid rounded '} id={'options'}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>


                <HelpContentHeader id={'addProduct'}>Como eu adiciono um produto?</HelpContentHeader>
                <p> Para adicionar uma nova lista, basta clicar no símbolo de + (Mais) como o da imagem abaixo. </p>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>
                <p> Após clicar, irá surgir uma tela e apenas o nome é obrigatório.</p>


                <HelpContentHeader id={'stipulatedPrice'}>O que significa Valor do produto?</HelpContentHeader>
                <p>
                    Significa o valor que você acha que vai pagar no produto, um valor estipulado, um valor que
                    você tem em mente para comprar o produto.
                </p>
                <p> Exemplificando:
                    Digamos que você esteja querendo comprar uma Geladeira. Você faz uma rápida pesquisa na internet
                    e olha os preços médios, após isso você deve ter um preço em mente, certo? É esse preço que você
                    colocará aqui, um preço estipulado.
                </p>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>

                <HelpContentHeader id={'spentPrice'}>O que significa valor gasto?</HelpContentHeader>
                <p>
                    Significa o valor que você realmente pagou no produto após ter comprado.
                    Lembra do valor estipulado? Que era o preço que você achava que iria pagar?
                    Então, aqui você coloca o valor que realmente pagou. Pode ser ele maior ou menor que o preço esperado,
                    afinal de contas, nem sempre os preços saem mais baratos que a gente espera.
                </p>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>

                <HelpContentHeader id={'editProduct'}>Como editar um produto?</HelpContentHeader>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>
                <p>Após clicar nos 3 pontos (como o da imagem) basta selecionar <strong>Editar</strong>, e então editar o produto que deseja</p>

                <HelpContentHeader id={'removeProduct'}>Como apaga um produto?</HelpContentHeader>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>
                <p>Após clicar nos 3 pontos (como o da imagem) basta selecionar <strong>Remover</strong>, e então editar o produto que deseja</p>

                <HelpContentHeader id={'removeMultiplesProducts'}>Como apaga vários produtos de uma vez?</HelpContentHeader>
                <p>Clicando nos <a href={'#options'}>3 pontinhos</a> Selecione a opção <strong>Selecionar</strong>
                    agora é só selecionar os produtos que deseja remover </p>

                <HelpContentHeader id={'copyProducts'}>Como copiar produtos pra outra lista?</HelpContentHeader>
                <p>Clicando nos <a href={'#options'}>3 pontinhos</a> Selecione a opção <strong>Selecionar. </strong>
                Agora selecione os produtos que deseja copiar para outra lista. Depois de selecionados,
                    basta clicar em <strong>Copiar</strong></p>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>

                <HelpContentHeader id={'moveProducts'}>Como mover produtos pra outra lista?</HelpContentHeader>
                <p>Clicando nos <a href={'#options'}>3 pontinhos</a> Selecione a opção <strong>Selecionar. </strong>
                    Agora selecione os produtos que deseja copiar para outra lista. Depois de selecionados,
                    basta clicar em <strong>Mover</strong></p>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>


                <SubTitle className={'pt-3'}>Card de produtos</SubTitle>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>

                <HelpContentHeader id={'cardCurrentPrice'}> O que significa Valor Atual?</HelpContentHeader>
                <p>
                    Significa a soma dos valores dos produtos que você já comprou (valor pago) com a soma do valor
                    dos produtos que ainda não comprou.
                </p>
                <p>Exemplificando: Digamos que você quer comprar um computado, para isso, vamos dizer que
                    um computador é composto por 2 peças principais: Monitor e gabinete. O monitor você achava que iria
                    pagar R$ 800 reais e o gabinete R$800 reais também. Você pegou uma boa promoção e pagou R$ 700 no
                    monitor, e a partir de agora, o valor total passará ser de R$ 1500 em vez de R$ 1600, somando 700
                    (valor pago no monitor) e 800 do valor do produto que ainda não comprou.
                </p>
                <HelpContentHeader id={'cardStipulatedPrice'}> O que significa Valor Estipulado?</HelpContentHeader>
                <p>Significa a soma do valor que acha que vai pagar de todos os produtos da lista.
                    Não considera o valor pago aqui.</p>


                <SubTitle className={'pt-3'}>Minha conta</SubTitle>
                <HelpContentHeader id={'changeProfileImage'}> Como colocar uma foto de perfil?</HelpContentHeader>
                <p>Para usar uma foto de perfil basta clicar no ícone que fica no canto superior direito, depois em Minha Conta.</p>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>
                <p>Chegando em sua conta, Basta clicar na foto de perfil e em seguida upload.</p>
                <p>Note que, existe um tamanho limite para realizar upload da imagem, então fique atento (a).</p>
                <Image className={'img-fluid rounded '}
                       src={"https://github.com/Rayllanderson/assets/blob/master/gerenciador-compras/ScreenShot_20210512014627.png?raw=true"}/>

                <HelpContentHeader id={'changeUsernameAndName'}> Como mudar Username ou nome?</HelpContentHeader>
                <p>Para mudar o nome ou username, siga os mesmos passos <a href={'#changeProfileImage'}> acima</a> mas,
                    ao invés de clicar na foto, clique em alterar dados, o botão azul. Irá surgir uma tela para você
                    alterar seus dados. O username deve conter, no mínimo, 3 caracteres, e, no máximo, 50. O nome é opcional.</p>
                <p>Observação: Ao alterar o <strong>username</strong>, é importante fazer Logoff e realizar o login novamente </p>

                <HelpContentHeader id={'changePassword'}> Como mudar a Senha?</HelpContentHeader>
                <p>Da mesma forma, siga os passos <a href={'#changeProfileImage'}> acima</a> até chegar na tela de
                    <i>Sua conta.</i>
                Agora é só clicar no botão amarelo de <strong>Alterar senha</strong>. A senha deve conter,
                    no mínimo, 3 caracteres, e, no máximo, 50. </p>

                <SubTitle className={'pt-3'}>Dúvidas?</SubTitle>
                <p>Ficou com alguma dúvida? Você pode me encontrar nas seguintes redes sociais:
                </p>
                <ul className={'navbar-nav'}>
                    <li className={"nav-item"}> <a target='_blank' href={'https://github.com/Rayllanderson'}><FiGithub/> Github </a></li>
                    <li className={"nav-item"}> <a target='_blank' href={'https://www.linkedin.com/in/rayllanderson/'}><FiLinkedin/> Linkedin </a></li>
                    <li className={"nav-item"}> <a target='_blank' href={'https://www.facebook.com/rayllanderson.goncalves/'}><FiFacebook/> Facebook </a></li>
                </ul>
            </HelpContent>
        </HelpCardContainer>
    )
}