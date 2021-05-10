import {Content} from "../styles";
import {Modal} from "react-bootstrap";
import {CloseButton} from "../../Buttons/CloseButton/closeButton";
import {PrimaryButton, SecondaryButton} from "../../Buttons/styles";
import {useContext} from "react";
import {ModalContext} from "../../../contexts/ModalContext";
import {InputPassword, InputText} from "../../Inputs";
import {AccountContext} from "../../../contexts/AccountContext";
import MyAlert from "../../Alert";
import {Avatar} from "../../ProfileImage/styles";

export function ChangeUserDataModal() {
    const {showChangeDataModal, closeChangeDataModal} = useContext(ModalContext);
    const {username, name, handleUsernameChange, handleNameChange, update} = useContext(AccountContext);
    return (
        <Modal centered show={showChangeDataModal} className={"rounded-0"} onHide={closeChangeDataModal}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title d-flex align-items-center">Alterar Dados</Modal.Title>
                    <CloseButton onClick={closeChangeDataModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <MyAlert/>
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
                    <PrimaryButton className={"btn"} onClick={update}> Alterar </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}

export function ChangePasswordModal() {
    const {showChangePasswordModal, closeChangePasswordModal} = useContext(ModalContext);
    const {password, handlePasswordChange, updatePassword} = useContext(AccountContext);
    return (
        <Modal centered show={showChangePasswordModal} className={"rounded-0"} onHide={closeChangePasswordModal}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title d-flex align-items-center">Alterar Senha</Modal.Title>
                    <CloseButton onClick={closeChangePasswordModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <MyAlert/>
                    <div>
                        <label>Nova Senha</label>
                        <InputPassword onChange={handlePasswordChange} value={password}/>
                    </div>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary"
                                     onClick={closeChangePasswordModal}>Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"} onClick={updatePassword}> Alterar </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}

export function PreviewPhotoModal(){
    const {showPreviewPhotoModal, closePreviewPhotoModal} = useContext(ModalContext);
    const {uploadFile} = useContext(AccountContext);
    return (
        <Modal centered show={showPreviewPhotoModal} className={"rounded-0"} onHide={closePreviewPhotoModal}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title d-flex align-items-center">Preview</Modal.Title>
                    <CloseButton onClick={closePreviewPhotoModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <MyAlert/>
                    <div className={'d-flex justify-content-center'}>
                    <Avatar id={'image'} alt={'Profile photo'}/>
                    </div>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary"
                                     onClick={closePreviewPhotoModal}>Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"} onClick={uploadFile}> Salvar foto </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}