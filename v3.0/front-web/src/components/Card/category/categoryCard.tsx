import {Form, ProgressBar} from "react-bootstrap";
import {CardContainer} from './styles'
import {FiEdit2, FiTrash} from "react-icons/all";
import {PrimaryButton, RedButton} from '../../Buttons/styles'
import {useContext, useEffect} from "react";
import {CardActionsContext} from "../../../context/cardActions";

interface Props {
    id: string,
    selectedItems: any[];
    addSelectItem: (value:any) => void;
    removeSelectItem: (value:any) => void;
}

export function CategoryCard({id, selectedItems, addSelectItem, removeSelectItem}: Props){

    const {checkBoxIsVisible, deleteButtonIsVisible, editButtonIsVisible} = useContext(CardActionsContext);

    const handleCheckBoxChange = (e: any) => {
        const value = e.target.value;
        if (e.target.checked) {
            addSelectItem(value);
        }else{
            removeSelectItem(value);
        }
    }

    // rascunhos...
    useEffect(() => {
        console.log(selectedItems.length)
        if (selectedItems.length > 0 ){
            console.log('mostrou botao');
        }else{
            console.log('sumiu botao');
        }
        console.log(selectedItems)
    }, [selectedItems.length])

    return(
        <CardContainer>
            <div className="col">
                <div className="card h-50">
                    <div className="card-body">
                        <div className={"addons"}>
                            {checkBoxIsVisible &&
                                <Form.Check type="checkbox" className="checkbox" value={id}
                                        onChange={handleCheckBoxChange}/>
                            }

                            {editButtonIsVisible && <EditButton/> }
                            {deleteButtonIsVisible && <DeleteButton/> }
                        </div>
                        <h5 className="card-title ">Supermercado</h5>
                        <p className="card-text ">R$ 250.00</p>
                        <ProgressBar now={50}  label={'50%'} title={`${50}% concluída`} />
                    </div>
                </div>
            </div>
        </CardContainer>
    )
}

function EditButton(){
    return(
        <PrimaryButton className="btn btn-sm"><FiEdit2/></PrimaryButton>
    )
}

function DeleteButton(){
    return(
         <RedButton className="btn btn-sm ms-2"><FiTrash/></RedButton>
    )
}

function removeItem(array:any[]){

}
