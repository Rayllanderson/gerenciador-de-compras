import {AccountCardContainer} from "../../components/Card/styles";
import ProfileImage from "../../components/ProfileImage";
import ProfileCard from "../../components/Card/ProfileCard";
import {useContext, useEffect} from "react";
import {CyanSecondaryButton, YellowButton} from "../../components/Buttons/styles";
import {Footer, InformationContainer} from './styles';
import {AccountContext} from "../../contexts/AccountContext";
import {ActionModalContext} from "../../contexts/ActionModalContext";
import {ChangeUserDataModal} from "../../components/Modal/account";

export function Account() {

    const {fetchUser, user} = useContext(AccountContext);
    const {openChangeUserDataModalAction} = useContext(ActionModalContext);

    useEffect(() => {
       fetchUser();
    }, [fetchUser])

    return (
        <AccountCardContainer className={'container mt-5 card appearFromBottom'}>
            <h5 className={'pt-4 pb-4'}>Sua Conta</h5>
            <div>
                <ProfileImage src={user.base64}/>
                <ProfileCard />
            </div>

            <InformationContainer>
                <p>Olá, <strong> {user.name}</strong>! </p>
                <p>Seu username é <strong>{user.username}</strong></p>
            </InformationContainer>

            <Footer className={'pb-3'}>
                <CyanSecondaryButton className={'btn mx-2'} onClick={openChangeUserDataModalAction}>Alterar Dados</CyanSecondaryButton>
                <YellowButton className={'btn'}>Alterar Senha</YellowButton>
            </Footer>

            <ChangeUserDataModal/>
        </AccountCardContainer>
    )
}