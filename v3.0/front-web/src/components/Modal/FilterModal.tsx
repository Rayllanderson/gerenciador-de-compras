import {Modal} from "react-bootstrap";
import {Content} from "./styles";
import {CloseButton} from "../Buttons/CloseButton/closeButton";
import {PrimaryButton, SecondaryButton} from "../Buttons/styles";

interface OptionsValuesData {
    name: string,
    value: string
}

export function FilterModal() {

    const categoryOptionsValues: OptionsValuesData[] = [{
        name: 'Nome',
        value: 'name'
    }, {
        name: 'Orçamento',
        value: 'budget'
    }];

    const productOptionsValues: OptionsValuesData[] = [{
        name: 'Nome',
        value: 'name'
    }, {
        name: 'Valor Estipulado',
        value: 'stipulatedPrice'
    },{
        name: 'Valor Pago',
        value: 'spentPrice'
    },{
        name: 'Comprados',
        value: 'purchased'
    }];

    return (
        <Modal centered show={true} className={"rounded-0"}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title d-flex align-items-center">Filtrar</Modal.Title>
                    <CloseButton/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <div className={'mb-3'}>
                        <label>Ordenar por</label>
                        <select className="form-select">
                            {productOptionsValues.map((item) =>
                                <option value={item.value} key={item.value}>{item.name}</option>
                            )}
                        </select>
                    </div>
                    <div className={'mb-3'}>
                        <label>Em Ordem</label>
                        <select className={'form-select'}>
                            <option value={'asc'}> Crescente</option>
                            <option value={'desc'}> Decrescente</option>
                        </select>
                    </div>
                    <div className={'mb-3'}>
                        <label>Resultados por página</label>
                        <input type={'number'} className={'form-control'} value={6}/>
                    </div>
                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary">Fechar</SecondaryButton>
                    <PrimaryButton className={"btn"}> Aplicar </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}