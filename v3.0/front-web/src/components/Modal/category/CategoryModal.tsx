import {Modal} from "react-bootstrap";
import {PrimaryButton, SecondaryButton} from '../../Buttons/styles';
import {Content} from '../styles';
import {CloseModalButton} from "../../Buttons/CloseButton/closeModalButton";
import {InputNumber, InputText} from "../../Inputs";
import '../styles.css'

export function CategoryModal() {
    return (
        <Modal centered animated show={false} className={"rounded-0"}>
            <Content>
                <Modal.Header style={{border: 'none'}}>
                    <Modal.Title className="modal-title">Nova Lista</Modal.Title>
                    <CloseModalButton/>
                </Modal.Header>
                <Modal.Body style={{border: 'none'}}>
                    <div className="mb-3">
                        <InputText placeholder={'Nome da lista'} value={''}/>
                    </div>

                    <div className="mb-3">
                        <InputNumber placeholder={'Orçamento R$'} value={''}/>
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
