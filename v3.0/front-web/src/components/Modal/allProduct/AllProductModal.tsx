import {Form, Modal} from "react-bootstrap";
import {Content} from "../styles";
import {CloseButton} from "../../Buttons/CloseButton/closeButton";
import {InputCheckbox, InputNumber, InputText} from "../../Inputs";
import {PrimaryButton, SecondaryButton} from "../../Buttons/styles";
import '../styles.css'
import {useContext, useEffect} from "react";
import {ModalContext} from "../../../contexts/ModalContext";
import MyAlert from "../../Alert";
import {ButtonWithLoader} from "../../Buttons";
import {LoadingContext} from "../../../contexts/LoadingContex";
import {AllProductContext} from "../../../contexts/AllProductContext";
import {CategoryResponseBody} from "../../../interfaces/categoryInterface";
import {CategoryContext} from "../../../contexts/CategoryContext";

export function AllProductModal() {

    const {showAddModal, closeAddModal} = useContext(ModalContext);
    const {name, handleNameChange, stipulatedPrice, action, handleStipulatedPriceChange
    , isPurchased, handleIsPurchasedChange, handleSpentPriceChange, spentPrice, submit, handleNewCategoryIdChange
    } = useContext(AllProductContext);
    const {btnIsLoading} = useContext(LoadingContext);
    const {categories, loadCategoriesNonPageable} = useContext(CategoryContext);

    useEffect(() => {
        loadCategoriesNonPageable();
    }, [loadCategoriesNonPageable])

    const title = action === 'save' ? 'Novo produto' : 'Editar produto';
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
                        <label className="form-label">Nome do produto</label>
                        <InputText placeholder={'Nome do produto'} value={name} onChange={handleNameChange}/>
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Valor do produto</label>
                        <InputNumber placeholder={'Quanto acha que vai pagar?'}
                                     value={stipulatedPrice} onChange={handleStipulatedPriceChange}/>
                    </div>

                    {
                        isPurchased && (
                            <div className="mb-3 transition-2 appearFromBottom">
                                <label className="form-label">Valor gasto no produto</label>
                                <InputNumber placeholder={'Quanto pagou no produto?'} value={spentPrice}
                                             onChange={handleSpentPriceChange}/>
                            </div>
                        )
                    }

                    <div className="mb-3">
                        <div className={'d-flex justify-content-start'}>
                            <label className="form-check-label" htmlFor="flexCheckDefault">
                                Já comprou o produto? &nbsp;
                            </label>
                            <InputCheckbox onChange={handleIsPurchasedChange} checked={isPurchased} />
                            <span>{isPurchased ? 'Sim' : 'Não'}</span>
                        </div>
                    </div>

                    <Form.Group >
                        <Form.Label>Escolha a lista de destino</Form.Label>
                        <select className="form-select" onChange={handleNewCategoryIdChange} defaultValue={'DEFAULT'}>
                            <option disabled value={'DEFAULT'}>Selecione a lista</option>
                            {categories.map((category: CategoryResponseBody) =>
                                <option value={category.id}  key={JSON.stringify(category)}>{category.name}</option>
                            )}
                        </select>
                    </Form.Group>

                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button"
                                     className="btn button-secondary" onClick={closeAddModal}>Fechar</SecondaryButton>
                    {
                        btnIsLoading ?
                            <ButtonWithLoader Button={PrimaryButton} type={'normal'}/> :
                            <PrimaryButton className={"btn"} onClick={submit}> Salvar </PrimaryButton>
                    }
                </Modal.Footer>
            </Content>
        </Modal>
    )
}