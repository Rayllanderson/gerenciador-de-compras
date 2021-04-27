import {useContext, useEffect, useState} from "react";
import {VisibilityCardItemContext} from "../../context/CardItemVisibilityContext";
import {SelectedItemsContext} from "../../context/SelectedItemsContext";
import {AddonsContainer} from "./styles";
import {PrimaryButton, RedButton} from "../Buttons/styles";
import {FiEdit2, FiTrash} from "react-icons/all";
import {CheckboxContainer} from '../Inputs/styles'
import {InputCheckboxSelectItems} from "../Inputs";
import {SelectItem} from "../../interfaces/selectItemInterface";

interface Props {
    id: string;
}

export function CardAddon({id}: Props) {

    const {checkBoxIsVisible, deleteButtonIsVisible, editButtonIsVisible} = useContext(VisibilityCardItemContext);
    const {handleCheckBoxChange} = useContext(SelectedItemsContext);

    const {selectedItems} = useContext(SelectedItemsContext)
    const [selectedItem, setSelectedItem] = useState<SelectItem>({id: '', isSelected: false})
    useEffect(() => {
        selectedItems.forEach((item) => {
            if (item.id == id) setSelectedItem(item);
        })
    }, [selectedItems])

    return (
        <AddonsContainer className={"addons"}>
            {checkBoxIsVisible && (
                <CheckboxContainer>
                    <div className={'checkbox'}>
                        <InputCheckboxSelectItems value={id} onChange={handleCheckBoxChange}
                                                  checked={selectedItem.isSelected}/>
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
