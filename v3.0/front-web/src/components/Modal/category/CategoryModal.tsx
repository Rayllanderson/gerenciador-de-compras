import {Modal} from "react-bootstrap";
import {PrimaryButton, SecondaryButton} from '../../Buttons/styles';
import {Content} from '../styles';
import {CloseButton} from "../../Buttons/CloseButton/closeButton";
import {InputNumber, InputText} from "../../Inputs";
import '../styles.css'
import {useContext} from "react";
import {ModalContext} from "../../../context/ModalContext";
import {CategoryResponseBody} from "../../../interfaces/categoryInterface";
import {CategoryContext} from "../../../context/CategoryContext";


interface Props {
    category?: CategoryResponseBody;
}

export function CategoryModal( {category}: Props) {
    const {showAddModal, closeAddModal} = useContext(ModalContext);
    const {save, budget, handleBudgetChange, name, handleNameChange} = useContext(CategoryContext)
    const title = !!category ? 'Editar lista' : 'Nova Lista';
    return (
        <Modal centered show={showAddModal} className={"rounded-0"}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title">{title}</Modal.Title>
                    <CloseButton onClick={closeAddModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <div className="mb-3">
                        <InputText placeholder={title} value={name} onChange={handleNameChange}/>
                    </div>

                    <div>
                        <InputNumber placeholder={'Orçamento R$'} value={budget} onChange={handleBudgetChange}/>
                    </div>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary" onClick={closeAddModal}>Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"} onClick={save}> Salvar </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}
