import {HelpCardContainer} from '../../components/Card/styles';
import {Title, SubTitle} from '../../components/Text/styles';
import {FiHelpCircle} from "react-icons/all";
import {Summary, SummaryHeader, SummaryItem} from './styles';
import React from "react";

export function HelpPage() {
    return (
        <HelpCardContainer className={'container card mt-5'}>
            <Title className={'pt-4'}> <FiHelpCircle/> Ajuda </Title>
            <SubTitle className={'pt-5'}>Sumário</SubTitle>
            <Summary className={'pt-2'}>
                <SummaryHeader> Listas </SummaryHeader>
                <SummaryItem>Como adicionar uma nova lista?</SummaryItem>
                <SummaryItem>O que significa a barra que fica nas listas?</SummaryItem>
                <SummaryItem>Como edita uma lista?</SummaryItem>
                <SummaryItem>Como apaga uma lista?</SummaryItem>
                <SummaryItem>Como apaga várias lista de uma vez?</SummaryItem>
                <SummaryItem>Duplicando Listas</SummaryItem>
                <SummaryItem>Como duplicar uma lista?</SummaryItem>

                <SummaryHeader className={'pt-3'}> Produtos </SummaryHeader>
                <SummaryItem>Como eu adiciono um produto?</SummaryItem>
                <SummaryItem>O que significa Valor do produto?</SummaryItem>
                <SummaryItem>O que significa valor gasto?</SummaryItem>
                <SummaryItem>Como editar um produto?</SummaryItem>
                <SummaryItem>Como editar um produto?</SummaryItem>
                <SummaryItem>Como apaga um produto?</SummaryItem>
                <SummaryItem>Como apaga vários produtos de uma vez?</SummaryItem>
                <SummaryItem>Como copiar um produto pra outra lista?</SummaryItem>
                <SummaryItem>Como mover um produto pra outra lista?</SummaryItem>

                <SummaryHeader className={'pt-3'}>Card de Produtos</SummaryHeader>
                <SummaryItem>O que significa Valor Atual?</SummaryItem>
                <SummaryItem>O que significa Valor Estipulado?</SummaryItem>

                <SummaryHeader className={'pt-3'}>Minha Conta</SummaryHeader>
                <SummaryItem>Como colocar uma foto de perfil?</SummaryItem>
                <SummaryItem>Como mudar Username ou nome?</SummaryItem>
                <SummaryItem>Como mudar a Senha?</SummaryItem>
            </Summary>
        </HelpCardContainer>
    )
}