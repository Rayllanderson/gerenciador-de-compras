import {Modal} from "react-bootstrap";
import {Content} from "./styles";
import {CloseButton} from "../Buttons/CloseButton/closeButton";
import {PrimaryButton, SecondaryButton} from "../Buttons/styles";
import {useCallback, useContext} from "react";
import {ActionModalContext} from "../../contexts/ActionModalContext";
import {ModalContext} from "../../contexts/ModalContext";
import {DEFAULT_NUMBER_OF_PAGE, PaginationContext} from "../../contexts/PaginationContext";

interface OptionsValuesData {
    name: string,
    value: string
}

export function FilterModal() {

    const {filterType} = useContext(ActionModalContext);
    const {showFilterModal, closeFilterModal} = useContext(ModalContext);
    const {
        handleSortChange, sort, handleSizeChange, size, order, handleOrderChange
        , setSort, setSize, setOrder, handlePageTypeChange, setPageType, pageType
    } = useContext(PaginationContext);

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
    }, {
        name: 'Valor Pago',
        value: 'spentPrice'
    }, {
        name: 'Comprados',
        value: 'purchased'
    }];

    const apply = useCallback(() => {
        closeFilterModal();
    }, [closeFilterModal])

    const cancel = useCallback(() => {
        setSort('');
        setOrder('asc');
        setSize(DEFAULT_NUMBER_OF_PAGE);
        closeFilterModal();
        setPageType({type: 'all'})
    }, [setSort, setOrder, setSize, closeFilterModal, setPageType])

    return (
        <Modal centered show={showFilterModal} className={"rounded-0"} onHide={cancel}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title d-flex align-items-center">Filtrar</Modal.Title>
                    <CloseButton onClick={cancel}/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <div className={'mb-3'}>
                        <label>Ordenar por</label>
                        <select className="form-select" onChange={handleSortChange} value={sort}>
                            {filterType === 'category' && categoryOptionsValues.map((item) =>
                                <option value={item.value} key={item.value}>{item.name}</option>
                            )}
                            {filterType === 'product' && productOptionsValues.map((item) =>
                                <option value={item.value} key={item.value}>{item.name}</option>
                            )}
                        </select>
                    </div>
                    <div className={'mb-3'}>
                        <label>Em Ordem</label>
                        <select className={'form-select'} onChange={handleOrderChange} value={order}>
                            <option value={'asc'}> Crescente</option>
                            <option value={'desc'}> Decrescente</option>
                        </select>
                    </div>
                    <div>
                        <label>Resultados por página</label>
                        <input type={'number'} className={'form-control'} value={size} onChange={handleSizeChange}/>
                    </div>

                    {filterType === 'product' &&
                    <div className={'mt-3'}>
                        <label>Listando</label>
                        <select className={'form-select'} onChange={handlePageTypeChange} value={pageType.type}>
                            <option value={'all'}> Todos os produtos</option>
                            <option value={'purchased'}> Produtos Comprados</option>
                            <option value={'non-purchased'}> Produtos Não Comprados</option>
                        </select>
                    </div>
                    }

                </Modal.Body>
                <Modal.Footer style={{border: 'none'}}>
                    <SecondaryButton type="button" className="btn button-secondary"
                                     onClick={cancel}>Cancelar</SecondaryButton>
                    <PrimaryButton className={"btn"} onClick={apply}> Aplicar </PrimaryButton>
                </Modal.Footer>
            </Content>
        </Modal>
    )
}