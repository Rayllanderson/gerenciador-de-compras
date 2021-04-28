import {Modal} from "react-bootstrap";
import {PrimaryButton, SecondaryButton} from '../../Buttons/styles';
import {Content} from '../styles';
import {CloseButton} from "../../Buttons/CloseButton/closeButton";
import {InputNumber, InputText} from "../../Inputs";
import '../styles.css'
import {useContext} from "react";
import {ModalContext} from "../../../context/ModalContext";

export function CategoryModal() {
    const {showAddModal, closeAddModal} = useContext(ModalContext);
    return (
        <Modal centered animated show={showAddModal} className={"rounded-0"}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title">Nova Lista</Modal.Title>
                    <CloseButton onClick={closeAddModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <div className="mb-3">
                        <InputText placeholder={'Nome da lista'} value={''}/>
                    </div>

                    <div className="">
                        <InputNumber placeholder={'Orçamento R$'} value={''}/>
                    </div>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary" onClick={closeAddModal}>Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"}> Salvar </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}
