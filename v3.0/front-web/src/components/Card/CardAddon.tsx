import {useContext, useEffect} from "react";
import {VisibilityCardItemContext} from "../../context/CardItemVisibilityContext";
import {CardItemActionContext} from "../../context/CardItemActionContext";
import {AddonsContainer} from "./styles";
import {Form} from "react-bootstrap";
import {PrimaryButton, RedButton} from "../Buttons/styles";
import {FiEdit2, FiTrash} from "react-icons/all";

interface Props {
    id: string;
}

export function CardAddon({id}: Props) {

    const {checkBoxIsVisible, deleteButtonIsVisible, editButtonIsVisible} = useContext(VisibilityCardItemContext);
    const {selectedItems, handleCheckBoxChange} = useContext(CardItemActionContext);

    useEffect(() => {
        console.log(selectedItems.length)
        if (selectedItems.length > 0) {
            console.log('mostrou botao');
        } else {
            console.log('sumiu botao');
        }
        console.log(selectedItems)
    }, [selectedItems.length])

    return (
        <AddonsContainer className={"addons"}>
            {checkBoxIsVisible &&
            <Form.Check type="checkbox" className="checkbox" value={id}
                        onChange={handleCheckBoxChange}/>
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
