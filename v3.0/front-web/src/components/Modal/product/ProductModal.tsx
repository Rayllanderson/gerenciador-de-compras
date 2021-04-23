import {Modal} from "react-bootstrap";
import {Content} from "../styles";
import {CloseModalButton} from "../../Buttons/CloseButton/closeModalButton";
import {InputCheckbox, InputNumber, InputText} from "../../Inputs";
import {PrimaryButton, SecondaryButton} from "../../Buttons/styles";
import '../styles.css'
import {useContext} from "react";
import {ProductModalContext} from "../../../context/ProductModalContext";

export function ProductModal() {

    const {checked, handleCheckBoxChange} = useContext(ProductModalContext)

    return (
        <Modal centered animated={'true'} show={false} className={"rounded-0"}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title">Novo Produto</Modal.Title>
                    <CloseModalButton/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <div className="mb-3">
                        <label className="form-label">Nome do produto</label>
                        <InputText placeholder={'Nome do produto'} value={''}/>
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Valor do produto</label>
                        <InputNumber placeholder={'Quanto acha que vai pagar?'} value={''}/>
                    </div>

                    {
                        checked && (
                            <div className="mb-3 transition-2">
                                <label className="form-label">Valor gasto no produto</label>
                                <InputNumber placeholder={'Quanto pagou no produto?'} value={''}/>
                            </div>
                        )
                    }

                    <div className="mb-3">
                        <div className={'d-flex justify-content-start'}>
                            <label className="form-check-label" htmlFor="flexCheckDefault">
                                Já comprou o produto? &nbsp;
                            </label>
                            <InputCheckbox handleChange={handleCheckBoxChange} value={''}/>
                            <span>{checked ? 'Sim' : 'Não'}</span>
                        </div>
                    </div>



                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary">Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"}> Salvar </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}