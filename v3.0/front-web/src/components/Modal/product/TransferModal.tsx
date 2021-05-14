import {Form, Modal,} from "react-bootstrap";
import {Content} from "../styles";
import {CloseButton} from "../../Buttons/CloseButton/closeButton";
import MyAlert from "../../Alert";
import {PrimaryButton, SecondaryButton} from "../../Buttons/styles";
import {useCallback, useContext, useEffect} from "react";
import {ModalContext} from "../../../contexts/ModalContext";
import {CategoryContext} from "../../../contexts/CategoryContext";
import {CategoryResponseBody} from "../../../interfaces/categoryInterface";
import {SelectedItemsContext} from "../../../contexts/SelectedItemsContext";
import {SelectItem} from "../../../interfaces/selectItemInterface";
import {ActionModalContext} from "../../../contexts/ActionModalContext";
import {ButtonWithLoader} from "../../Buttons";
import {LoadingContext} from "../../../contexts/LoadingContex";

interface Props {
    copyAction: () => void,
    moveAction: () => void,
    handleCategoryIdChange: (e: any) => void;
}

export function TransferModal({copyAction, moveAction, handleCategoryIdChange}: Props) {
    const {showTransferModal} = useContext(ModalContext);
    const {categories, loadCategoriesNonPageable} = useContext(CategoryContext);

    const {selectedItems} = useContext(SelectedItemsContext);
    const {transferModalTitle, closeTransferModalAction} = useContext(ActionModalContext);
    const {btnIsLoading} = useContext(LoadingContext);

    useEffect(() => {
        loadCategoriesNonPageable();
    }, [loadCategoriesNonPageable])

    const action = useCallback(
        () => {
            transferModalTitle === 'Copiar' ? copyAction() : moveAction();
        },
        [copyAction, moveAction, transferModalTitle],
    );

    return (
        <Modal centered show={showTransferModal} className={"rounded-0"} onHide={closeTransferModalAction}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title">{transferModalTitle} produtos</Modal.Title>
                    <CloseButton onClick={closeTransferModalAction}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <MyAlert/>
                    <h5>Produtos selecionados</h5>
                    {selectedItems.map((product: SelectItem) => <p key={JSON.stringify(product)}>{product.name}</p>)}
                    <Form.Group >
                        <Form.Label>Escolha a lista para {transferModalTitle.toLocaleLowerCase()}</Form.Label>
                        <select className="form-select" onChange={handleCategoryIdChange} defaultValue={'DEFAULT'}>
                            <option disabled value={'DEFAULT'}>Selecione a lista</option>
                            {categories.map((category: CategoryResponseBody) =>
                                <option value={category.id}  key={JSON.stringify(category)}>{category.name}</option>
                            )}
                        </select>
                    </Form.Group>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button"
                                     className="btn button-secondary"
                                     onClick={closeTransferModalAction}>Fechar</SecondaryButton>
                    {
                        btnIsLoading ?
                            <ButtonWithLoader Button={PrimaryButton} type={'normal'}/> :
                            <PrimaryButton className={"btn"} onClick={action}> {transferModalTitle} </PrimaryButton>
                    }
                </Modal.Footer>
            </Content>
        </Modal>
    )
}