import {HomeContainer, BorderWrapper} from "./styles";
import {useContext} from "react";
import {AuthContext} from "../../contexts/AuthContext";
import {SubTitle, Title} from "../../components/Text/styles";
import {Link} from 'react-router-dom';

export default function Home() {

    const {user} = useContext(AuthContext);
    // @ts-ignore
    const name = user.name ? user.name : 'Convidado';

    return (
        <BorderWrapper className={'container card mt-5'}>
            <HomeContainer className={'container'}>
                <Title className={'pt-5'}>Olá, {name}</Title>
                <SubTitle className={'pt-2'}>O que deseja acessar?</SubTitle>
                <div className={'buttons pt-4 pb-5'}>
                    <Link to={'/categories'} className={'btn link-primary transparent'}>Categorias</Link>
                    <Link to={'/all-products'} className={'btn link-primary transparent'}>Todos os produtos</Link>
                    <Link to={'/account'} className={'btn link-primary transparent'}>Minha conta</Link>
                    <Link to={'/statistics'} className={'btn link-primary transparent'}>Estatísticas</Link>
                    <Link to={'/help'} className={'btn link-primary transparent'}>Ajuda</Link>
                </div>
            </HomeContainer>
        </BorderWrapper>
    )
}