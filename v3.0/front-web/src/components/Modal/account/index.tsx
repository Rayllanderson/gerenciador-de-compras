import {Content} from "../styles";
import {Modal} from "react-bootstrap";
import {CloseButton} from "../../Buttons/CloseButton/closeButton";
import {PrimaryButton, SecondaryButton} from "../../Buttons/styles";
import {useContext} from "react";
import {ModalContext} from "../../../contexts/ModalContext";
import {InputText} from "../../Inputs";

export function ChangeDataModal() {
    const {showChangeDataModal, closeChangeDataModal} = useContext(ModalContext);
    return (
        <Modal centered show={showChangeDataModal} className={"rounded-0"} onHide={closeChangeDataModal}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title d-flex align-items-center">Atenção</Modal.Title>
                    <CloseButton onClick={closeChangeDataModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <div className={'mb-3'}>
                        <label>Nome</label>
                        <InputText/>
                    </div>
                    <div>
                        <label>Username</label>
                        <InputText/>
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