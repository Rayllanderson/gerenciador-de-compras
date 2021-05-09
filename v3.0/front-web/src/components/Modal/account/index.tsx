import {Content} from "../styles";
import {Modal} from "react-bootstrap";
import {CloseButton} from "../../Buttons/CloseButton/closeButton";
import {PrimaryButton, SecondaryButton} from "../../Buttons/styles";
import {useContext} from "react";
import {ModalContext} from "../../../contexts/ModalContext";
import {InputPassword, InputText} from "../../Inputs";
import {AccountContext} from "../../../contexts/AccountContext";

export function ChangeUserDataModal() {
    const {showChangeDataModal, closeChangeDataModal} = useContext(ModalContext);
    const {username, name, handleUsernameChange, handleNameChange} = useContext(AccountContext);
    return (
        <Modal centered show={showChangeDataModal} className={"rounded-0"} onHide={closeChangeDataModal}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title d-flex align-items-center">Alterar Dados</Modal.Title>
                    <CloseButton onClick={closeChangeDataModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <div className={'mb-3'}>
                        <label>Nome</label>
                        <InputText onChange={handleNameChange} value={name}/>
                    </div>
                    <div>
                        <label>Username</label>
                        <InputText onChange={handleUsernameChange} value={username}/>
                    </div>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary"
                                     onClick={closeChangeDataModal}>Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"} onClick={() => {}}> Alterar </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}

export function ChangePasswordModal() {
    const {showChangePasswordModal, closeChangePasswordModal} = useContext(ModalContext);
    const {password, handlePasswordChange} = useContext(AccountContext);
    return (
        <Modal centered show={showChangePasswordModal} className={"rounded-0"} onHide={closeChangePasswordModal}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title d-flex align-items-center">Alterar Senha</Modal.Title>
                    <CloseButton onClick={closeChangePasswordModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <div>
                        <label>Nova Senha</label>
                        <InputPassword onChange={handlePasswordChange} value={password}/>
                    </div>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary"
                                     onClick={closeChangePasswordModal}>Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"} onClick={() => {}}> Alterar </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}