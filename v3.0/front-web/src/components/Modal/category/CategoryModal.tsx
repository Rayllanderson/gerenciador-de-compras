import {Modal} from "react-bootstrap";
import {PrimaryButton, SecondaryButton} from '../../Buttons/styles';
import {Content} from '../styles';
import {CloseButton} from "../../Buttons/CloseButton/closeButton";
import {InputNumber, InputText} from "../../Inputs";
import '../styles.css'
import {useContext} from "react";
import {ModalContext} from "../../../contexts/ModalContext";
import {CategoryContext} from "../../../contexts/CategoryContext";
import MyAlert from "../../Alert";


export function CategoryModal() {
    const {showAddModal, closeAddModal} = useContext(ModalContext);
    const {budget, handleBudgetChange, name, handleNameChange, submit, action} = useContext(CategoryContext);

    const title = action === 'edit' ? 'Editar lista' : 'Nova Lista';
    return (
        <Modal centered show={showAddModal} className={"rounded-0"} onHide={closeAddModal}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title">{title}</Modal.Title>
                    <CloseButton onClick={closeAddModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <MyAlert />
                    <div className="mb-3">
                        <InputText placeholder={title} value={name} onChange={handleNameChange}/>
                    </div>
                    <div>
                        <InputNumber placeholder={'Orçamento R$'} value={budget} onChange={handleBudgetChange}/>
                    </div>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary" onClick={closeAddModal}>Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"} onClick={submit}> Salvar </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}
