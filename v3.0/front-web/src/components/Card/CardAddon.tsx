import {useCallback, useContext, useEffect, useState} from "react";
import {VisibilityCardItemContext} from "../../context/CardItemVisibilityContext";
import {SelectedItemsContext} from "../../context/SelectedItemsContext";
import {AddonsContainer} from "./styles";
import {PrimaryButton, RedButton} from "../Buttons/styles";
import {FiEdit2, FiTrash} from "react-icons/all";
import {CheckboxContainer} from '../Inputs/styles'
import {InputCheckbox} from "../Inputs";
import {SelectItem} from "../../interfaces/selectItemInterface";
import {createAnEmptySelectedItem} from "../../utils/selectItemUtil";

interface Props {
    id: string;
}

export function CardAddon({id}: Props) {

    const {checkBoxIsVisible, deleteButtonIsVisible, editButtonIsVisible} = useContext(VisibilityCardItemContext);
    const {handleCheckBoxChange, removeItemFromArray} = useContext(SelectedItemsContext);

    const {selectedItems} = useContext(SelectedItemsContext)
    const [selectedItem, setSelectedItem] = useState<SelectItem>(createAnEmptySelectedItem)

    useEffect(() => {
        const hasNoItemsSelected = selectedItems.length === 0;
        if (hasNoItemsSelected) setSelectedItem(createAnEmptySelectedItem);
        selectedItems.forEach((item: SelectItem) => {
            if (item.id === id.toString()) setSelectedItem(item);
        })
    }, [selectedItems, id])

    const handleClick = useCallback((e:any) => {
        const isChecked = !e.target.checked;
        if (isChecked) {
            selectedItem.isSelected = false;
            removeItemFromArray(selectedItem)
        }
    }, [selectedItem, removeItemFromArray])

    return (
        <AddonsContainer className={"addons"}>
            {checkBoxIsVisible && (
                <CheckboxContainer>
                    <div className={'checkbox'}>
                        <InputCheckbox value={id} onChange={handleCheckBoxChange}
                                       checked={selectedItem.isSelected} onClick={handleClick}/>
                    </div>
                </CheckboxContainer>
            )
            }
            {editButtonIsVisible && <EditButton/>}
            {deleteButtonIsVisible && <DeleteButton/>}
        </AddonsContainer>
    )
}


function EditButton() {
    return (
        <PrimaryButton className="btn btn-sm"><FiEdit2/></PrimaryButton>
    )
}

function DeleteButton() {
    return (
        <RedButton className="btn btn-sm ms-2"><FiTrash/></RedButton>
    )
}
