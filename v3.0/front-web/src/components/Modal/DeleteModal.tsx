import {Modal} from "react-bootstrap";
import {Content} from "./styles";
import {CloseButton} from "../Buttons/CloseButton/closeButton";
import {PrimaryButton, SecondaryButton} from "../Buttons/styles";
import {useContext} from "react";
import {ModalContext} from "../../contexts/ModalContext";
import {LoadingContext} from "../../contexts/LoadingContex";
import {ButtonWithLoader} from "../Buttons";

interface Props {
    text: string,
    action: () => void;
}

export function DeleteModal({text, action}: Props) {
    const {showRemoveModal, closeRemoveModal} = useContext(ModalContext);
    const {btnIsLoading} = useContext(LoadingContext);
    return (
        <Modal centered show={showRemoveModal} className={"rounded-0"} onHide={closeRemoveModal}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title d-flex align-items-center">Atenção</Modal.Title>
                    <CloseButton onClick={closeRemoveModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <p>{text}</p>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary"
                                     onClick={closeRemoveModal}>Fechar</SecondaryButton>
                    {
                        btnIsLoading ?
                            <ButtonWithLoader Button={PrimaryButton} type={'normal'}/> :
                            <PrimaryButton className={"btn"} onClick={action}> Excluir </PrimaryButton>
                    }
                </Modal.Footer>
            </Content>
        </Modal>
    )
}