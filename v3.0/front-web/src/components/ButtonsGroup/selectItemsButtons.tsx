import {SelectButtonsContainer} from './styles'
import {FiX} from "react-icons/all";
import {ReactNode, useContext} from "react";
import {SelectedItemsContext} from "../../context/SelectedItemsContext";
import {VisibilityCardItemContext} from "../../context/CardItemVisibilityContext";

interface Props {
    children: ReactNode;
}
export function SelectItemsButtons({children}: Props) {
    const {hasAnyItemSelected, selectedItems, removeAllSelectedItems} = useContext(SelectedItemsContext);
    const {hideCheckBox} = useContext(VisibilityCardItemContext);

    function closeCard() {
        hideCheckBox();
        removeAllSelectedItems();
    }
    return (
        <SelectButtonsContainer show={hasAnyItemSelected()} className={'container mb-4 appearFromBottom'}>
            <button
                className="position-absolute top-0 start-100 translate-middle badge close-card" onClick={closeCard}>
                <FiX size={23}/>
            </button>

            <div>
                <p className={'text-center'}> {selectedItems.length} selecionados</p>
            </div>
            <div className={"buttons"}>
                {children}
            </div>
        </SelectButtonsContainer>
    )
}