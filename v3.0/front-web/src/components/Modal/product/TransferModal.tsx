import {Form, Modal,} from "react-bootstrap";
import {Content} from "../styles";
import {CloseButton} from "../../Buttons/CloseButton/closeButton";
import MyAlert from "../../Alert";
import {PrimaryButton, SecondaryButton} from "../../Buttons/styles";
import {useContext, useEffect} from "react";
import {ModalContext} from "../../../contexts/ModalContext";
import {CategoryContext} from "../../../contexts/CategoryContext";
import {CategoryResponseBody} from "../../../interfaces/categoryInterface";
import {SelectedItemsContext} from "../../../contexts/SelectedItemsContext";
import {SelectItem} from "../../../interfaces/selectItemInterface";
import {ActionModalContext} from "../../../contexts/ActionModalContext";
import {ProductContext} from "../../../contexts/ProductContext";

export function TransferModal() {
    const {showTransferModal, closeTransferModal} = useContext(ModalContext);
    const {categories, loadCategoriesNonPageable} = useContext(CategoryContext);
    const {handleNewCategoryIdChange} = useContext(ProductContext);
    const {selectedItems} = useContext(SelectedItemsContext);
    const {action, transferModalTitle} = useContext(ActionModalContext);

    useEffect(() => {
        loadCategoriesNonPageable();
    }, [loadCategoriesNonPageable])

    return (
        <Modal centered show={showTransferModal} className={"rounded-0"} onHide={closeTransferModal}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title">{transferModalTitle} produtos</Modal.Title>
                    <CloseButton onClick={closeTransferModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <MyAlert/>
                    <h5>Produtos selecionados</h5>
                    {selectedItems.map((product: SelectItem) => <p key={JSON.stringify(product)}>{product.name}</p>)}
                    <Form.Group >
                        <Form.Label>Escolha a categoria para {transferModalTitle.toLocaleLowerCase()}</Form.Label>
                        <Form.Control as="select" onChange={handleNewCategoryIdChange}>
                            {categories.map((category: CategoryResponseBody) =>
                                <option value={category.id}  key={JSON.stringify(category)}>{category.name}</option>
                            )}
                        </Form.Control>
                    </Form.Group>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button"
                                     className="btn button-secondary"
                                     onClick={closeTransferModal}>Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"} onClick={action}> {transferModalTitle} </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}