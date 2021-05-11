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
import {ActionModalContext} from "../../../contexts/ActionModalContext";
import {formatBytes, getPhotoSize} from "../../../utils/profilePhotoUtil";
import {ButtonWithLoader} from "../../Buttons";
import {LoadingContext} from "../../../contexts/LoadingContex";

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
    const {btnIsLoading} = useContext(LoadingContext);
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
                    {
                        btnIsLoading ?
                            <ButtonWithLoader Button={PrimaryButton} type={'normal'}/> :
                            <PrimaryButton className={"btn"} onClick={updatePassword}> Alterar </PrimaryButton>
                    }
                </Modal.Footer>
            </Content>
        </Modal>
    )
}

export function PreviewPhotoModal(){
    const {showPreviewPhotoModal} = useContext(ModalContext);
    const {closePreviewPhotoModalAction} = useContext(ActionModalContext);
    const {uploadFile, photo} = useContext(AccountContext);
    const MAX_UPLOAD_FILE_SIZE = 204800;
    const currentImageSize = getPhotoSize(photo) as number;
    const cannotUpload: boolean = currentImageSize > MAX_UPLOAD_FILE_SIZE;
    return (
        <Modal centered show={showPreviewPhotoModal} className={"rounded-0"} onHide={closePreviewPhotoModalAction}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title d-flex align-items-center">Preview</Modal.Title>
                    <CloseButton onClick={closePreviewPhotoModalAction}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <MyAlert/>
                    <div className={'text-center align-self-lg-end'}>
                        <p>Tamanho Máximo: {formatBytes(MAX_UPLOAD_FILE_SIZE)}</p>
                        <p>Tamanho da imagem: <span style={{color: cannotUpload ? 'var(--red)' : 'var(--green)'}}>
                                {formatBytes(currentImageSize)} </span> </p>
                        {cannotUpload && <p style={{color: 'var(--red)'}}>Tamanho excedido, tente uma foto menor</p>}
                    </div>
                    <div className={'d-flex justify-content-center'}>
                    <Avatar id={'image'} alt={'Profile photo'}/>
                    </div>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary"
                                     onClick={closePreviewPhotoModalAction}>Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"} onClick={uploadFile} disabled={cannotUpload}> Salvar foto </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}
