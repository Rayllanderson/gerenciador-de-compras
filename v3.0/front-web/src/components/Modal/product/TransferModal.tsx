import {Form, Modal,} from "react-bootstrap";
import {Content} from "../styles";
import {CloseButton} from "../../Buttons/CloseButton/closeButton";
import MyAlert from "../../Alert";
import {InputCheckbox, InputNumber, InputText} from "../../Inputs";
import {PrimaryButton, SecondaryButton} from "../../Buttons/styles";
import {useContext} from "react";
import {ModalContext} from "../../../contexts/ModalContext";
import {CategoryContext} from "../../../contexts/CategoryContext";
import {CategoryResponseBody} from "../../../interfaces/categoryInterface";
import {SelectedItemsContext} from "../../../contexts/SelectedItemsContext";
import {SelectItem} from "../../../interfaces/selectItemInterface";

export function TransferModal() {
    const {showTransferModal, closeTransferModal} = useContext(ModalContext);
    const {categories} = useContext(CategoryContext);
    const {selectedItems} = useContext(SelectedItemsContext);
    return (
        <Modal centered animated={'true'} show={true} className={"rounded-0"} onHide={closeTransferModal}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title">Mover / copiar produtos</Modal.Title>
                    <CloseButton onClick={closeTransferModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <MyAlert/>
                    <h5>Produtos selecionados</h5>
                    {selectedItems.map((product: SelectItem) => <p>{product.name}</p>)}
                    <Form.Group >
                        <Form.Label>Escolha a categoria para mover / copiar</Form.Label>
                        <Form.Control as="select">
                            {categories.map((category: CategoryResponseBody) =>
                                <option value={category.id}>{category.name}</option>
                            )}
                        </Form.Control>
                    </Form.Group>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button"
                                     className="btn button-secondary"
                                     onClick={closeTransferModal}>Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"} onClick={() => {
                    }}> Mover / Copiar </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}