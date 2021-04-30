import {ReactNode, useCallback, useContext, useEffect, useState} from "react";
import {VisibilityCardItemContext} from "../../context/CardItemVisibilityContext";
import {SelectedItemsContext} from "../../context/SelectedItemsContext";
import {AddonsContainer} from "./styles";
import {CheckboxContainer} from '../Inputs/styles'
import {InputCheckbox} from "../Inputs";
import {SelectItem} from "../../interfaces/selectItemInterface";
import {createAnEmptySelectedItem} from "../../utils/selectItemUtil";

interface Props {
    id: string,
    name: string,
    children: ReactNode;
}

export function CardAddon({id, name, children}: Props) {

    const {checkBoxIsVisible} = useContext(VisibilityCardItemContext);
    const {handleCheckBoxChange, removeItemFromArray} = useContext(SelectedItemsContext);

    const {selectedItems} = useContext(SelectedItemsContext)
    const [selectedItem, setSelectedItem] = useState<SelectItem>(createAnEmptySelectedItem)

    useEffect(() => {
        const hasNoItemsSelected = selectedItems.length === 0;
        if (hasNoItemsSelected) setSelectedItem(createAnEmptySelectedItem);
        selectedItems.forEach((item: SelectItem) => {
            if (item.id === id) setSelectedItem(item);
        })
    }, [selectedItems, id])

    const handleClick = useCallback((e:any) => {
        const isChecked = !e.target.checked;
        if (isChecked) {
            selectedItem.isSelected = false;
            removeItemFromArray(selectedItem);
        }
    }, [selectedItem, removeItemFromArray])

    return (
        <AddonsContainer className={"addons"}>
            {checkBoxIsVisible && (
                <CheckboxContainer>
                    <div className={'checkbox'}>
                        <InputCheckbox value={JSON.stringify({id, name})} onChange={handleCheckBoxChange}
                                       checked={selectedItem.isSelected} onClick={handleClick}/>
                    </div>
                </CheckboxContainer>
            )
            }
            {children}
        </AddonsContainer>
    )
}
