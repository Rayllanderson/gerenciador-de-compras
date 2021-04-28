import {Modal} from "react-bootstrap";
import {Content} from "./styles";
import {CloseButton} from "../Buttons/CloseButton/closeButton";
import {PrimaryButton, SecondaryButton} from "../Buttons/styles";

interface Props {
    text: string;
}

export function DeleteModal({text}:Props) {
    return (
        <Modal centered animated={'true'} show={false} className={"rounded-0"}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title d-flex align-items-center">Atenção</Modal.Title>
                    <CloseButton/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <p>{text}</p>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary">Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"}> Excluir </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}