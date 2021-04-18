import {CyanSecondaryButton, RedButton} from "../../Buttons/styles";
import {SelectButtonsContainer} from './styles'
import {FiX} from "react-icons/all";

export function SelectButtons() {
    return (
        <SelectButtonsContainer className={'container mb-4'}>
            <button
                className="position-absolute top-0 start-100 translate-middle badge close-card">
                <FiX size={23}/>
            </button>

            <div>
                <p className={'text-center'}> 3 selecionados</p>
            </div>
            <div className={"buttons"}>
                <CyanSecondaryButton className={'btn '} title={'Duplicar selecionados'}>Duplicar</CyanSecondaryButton>

                <RedButton className={'btn ms-4'} title={'Deletar selecionados'}>Deletar </RedButton>
            </div>
        </SelectButtonsContainer>
    )
}