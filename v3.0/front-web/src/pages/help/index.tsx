import {HelpCardContainer} from '../../components/Card/styles';
import {Title, SubTitle} from '../../components/Text/styles';
import {FiHelpCircle} from "react-icons/all";
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
                <SummaryItem>O que significa Valor Atual?</SummaryItem>
                <SummaryItem>O que significa Valor Estipulado?</SummaryItem>

                <SummaryHeader className={'pt-3'}>Minha Conta</SummaryHeader>
                <SummaryItem>Como colocar uma foto de perfil?</SummaryItem>
                <SummaryItem>Como mudar Username ou nome?</SummaryItem>
                <SummaryItem>Como mudar a Senha?</SummaryItem>
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

            </HelpContent>

        </HelpCardContainer>
    )
}