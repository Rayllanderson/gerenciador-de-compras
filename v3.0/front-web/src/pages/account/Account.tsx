import {AccountCardContainer} from "../../components/Card/styles";
import ProfileImage from "../../components/ProfileImage";
import ProfileCard from "../../components/Card/ProfileCard";
import React, {useContext, useEffect} from "react";
import {CyanSecondaryButton, YellowButton} from "../../components/Buttons/styles";
import {Footer, InformationContainer} from './styles';
import {AccountContext} from "../../contexts/AccountContext";
import {ActionModalContext} from "../../contexts/ActionModalContext";
import {ChangePasswordModal, ChangeUserDataModal, PreviewPhotoModal} from "../../components/Modal/account";
import {ConfirmModal} from "../../components/Modal/ConfirmModal";
import {NameLoader} from "../../components/Loader/account";
import {LoadingContext} from "../../contexts/LoadingContex";
import {FiUser} from "react-icons/fi";

export function Account() {

    const {fetchUser, user} = useContext(AccountContext);
    const {openChangeUserDataModalAction, openChangePasswordModalAction} = useContext(ActionModalContext);
    const {isLoading} = useContext(LoadingContext);

    useEffect(() => {
       fetchUser();
    }, [fetchUser])

    return (
        <AccountCardContainer className={'container mt-5 card appearFromBottom'}>
            <h5 className={'pt-4 pb-4'}> <FiUser/> Sua Conta</h5>
            <div>
                <ProfileImage src={user.base64}/>
                <ProfileCard />
            </div>

            <InformationContainer>
                <p>Olá,  {isLoading ? <NameLoader/> : <strong> {user.name + '!'}</strong>} </p>
                <p>Seu username é  {isLoading ? <NameLoader/> : <strong>{user.username}</strong>}</p>
            </InformationContainer>

            <Footer className={'pb-3'}>
                <CyanSecondaryButton className={'btn mx-2'} onClick={openChangeUserDataModalAction}>Alterar Dados</CyanSecondaryButton>
                <YellowButton className={'btn'} onClick={openChangePasswordModalAction}>Alterar Senha</YellowButton>
            </Footer>

            <ChangeUserDataModal/>
            <ChangePasswordModal/>
            <PreviewPhotoModal/>
            <ConfirmModal/>
        </AccountCardContainer>
    )
}