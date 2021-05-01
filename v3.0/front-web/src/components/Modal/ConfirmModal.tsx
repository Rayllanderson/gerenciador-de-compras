import {Modal} from "react-bootstrap";
import {Content} from "./styles";
import {CloseButton} from "../Buttons/CloseButton/closeButton";
import {PrimaryButton, SecondaryButton} from "../Buttons/styles";
import {useContext} from "react";
import {ActionModalContext} from "../../contexts/ActionModalContext";
import {ModalContext} from "../../contexts/ModalContext";


export function ConfirmModal() {
    const {action, confirmModalText} = useContext(ActionModalContext);
    const {showConfirmModal, closeConfirmModal} = useContext(ModalContext)
    return (
        <Modal centered show={showConfirmModal} className={"rounded-0"} onHide={closeConfirmModal}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title d-flex align-items-center">Atenção</Modal.Title>
                    <CloseButton onClick={closeConfirmModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <p>{confirmModalText}</p>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary"
                                     onClick={closeConfirmModal}>Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"} onClick={action}> Sim </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}