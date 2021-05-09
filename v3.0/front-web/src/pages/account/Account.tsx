import {AccountCardContainer} from "../../components/Card/styles";
import ProfileImage from "../../components/ProfileImage";
import ProfileCard from "../../components/Card/ProfileCard";
import {useCallback, useContext, useEffect, useState} from "react";
import {CyanSecondaryButton, YellowButton} from "../../components/Buttons/styles";
import {Footer, InformationContainer} from './styles';
import {AccountContext} from "../../contexts/AccountContext";

export function Account() {
    const [opacity, setOpacity] = useState<number>(0);
    const [cardIsOpen, setCardIsOpen] = useState<boolean>(false)

    const showCard = useCallback(() => {
        setOpacity(1);
        setCardIsOpen(true);
    }, [setOpacity, setCardIsOpen]);

    const hideCard = useCallback(() => {
        setOpacity(0);
        setCardIsOpen(false);
    }, [setOpacity, setCardIsOpen]);

    const toggleCardVisibility = useCallback(() => {
        if (cardIsOpen) hideCard();
        else showCard();
    }, [showCard, hideCard, cardIsOpen]);
//  criar um context pra essas funções ae... adeus. raios caindo

    const {fetchUser, user} = useContext(AccountContext);

    useEffect(() => {
       fetchUser();
    }, [fetchUser])

    return (
        <AccountCardContainer className={'container mt-5 card appearFromBottom'}>
            <h5 className={'pt-4 pb-4'}>Sua Conta</h5>
            <div>
                <ProfileImage src={user.base64} click={toggleCardVisibility}/>
                <ProfileCard opacity={opacity}/>
            </div>

            <InformationContainer>
                <p>Olá, <strong> {user.name}</strong>! </p>
                <p>Seu username é <strong>{user.username}</strong></p>
            </InformationContainer>

            <Footer className={'pb-3'}>
                <CyanSecondaryButton className={'btn mx-2'}>Alterar Dados</CyanSecondaryButton>
                <YellowButton className={'btn'}>Alterar Senha</YellowButton>
            </Footer>
        </AccountCardContainer>
    )
}