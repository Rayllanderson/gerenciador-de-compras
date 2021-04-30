import {Modal} from "react-bootstrap";
import {Content} from "../styles";
import {CloseButton} from "../../Buttons/CloseButton/closeButton";
import {InputCheckbox, InputNumber, InputText} from "../../Inputs";
import {PrimaryButton, SecondaryButton} from "../../Buttons/styles";
import '../styles.css'
import {useContext} from "react";
import {ModalContext} from "../../../context/ModalContext";
import {ProductContext} from "../../../context/ProductContext";

export function ProductModal() {

    const {showAddModal, closeAddModal} = useContext(ModalContext);
    const {name, handleNameChange, stipulatedPrice, action, handleStipulatedPriceChange
    , isPurchased, handleIsPurchasedChange, handleSpentPriceChange, spentPrice} = useContext(ProductContext)

    const title = action === 'save' ? 'Novo produto' : 'Editar produto';
    return (
        <Modal centered animated={'true'} show={showAddModal} className={"rounded-0"} onHide={closeAddModal}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title">{title}</Modal.Title>
                    <CloseButton onClick={closeAddModal}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
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

                    <div >
                        <div className={'d-flex justify-content-start'}>
                            <label className="form-check-label" htmlFor="flexCheckDefault">
                                Já comprou o produto? &nbsp;
                            </label>
                            <InputCheckbox onChange={handleIsPurchasedChange} checked={isPurchased} />
                            <span>{isPurchased ? 'Sim' : 'Não'}</span>
                        </div>
                    </div>

                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button"
                                     className="btn button-secondary" onClick={closeAddModal}>Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"}> Salvar </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}