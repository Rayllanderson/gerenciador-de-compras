import {SelectButtonsContainer} from './styles'
import {ReactNode, useContext} from "react";
import {SelectedItemsContext} from "../../contexts/SelectedItemsContext";
import {VisibilityCardItemContext} from "../../contexts/CardItemVisibilityContext";
import {CloseButton} from "../Buttons/CloseButton/closeButton";

interface Props {
    children: ReactNode;
}

export function SelectItemsButtons({children}: Props) {
    const {hasAnyItemSelected, selectedItems, clearSelectedItems} = useContext(SelectedItemsContext);
    const {hideCheckBox} = useContext(VisibilityCardItemContext);

    function closeCard() {
        hideCheckBox();
        clearSelectedItems();
    }

    return (
        <SelectButtonsContainer show={hasAnyItemSelected()} className={'container mb-4 appearFromBottom'}>
            <div className={'d-flex justify-content-end close-card'}>
                <CloseButton onClick={closeCard}/>
            </div>
            <div>
                <p className={'text-center'}> {selectedItems.length} selecionados</p>
            </div>
            <div className={"buttons"}>
                {children}
            </div>
        </SelectButtonsContainer>
    )
}