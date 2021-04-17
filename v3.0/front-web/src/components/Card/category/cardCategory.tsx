import {Form, ProgressBar} from "react-bootstrap";
import {CardContainer} from './styles'
import {FiEdit2, FiTrash} from "react-icons/all";
import {PrimaryButton, RedButton} from '../../Buttons/styles'

export function CardCategory(){
    return(
        <CardContainer>
            <div className="col">
                <div className="card h-50">
                    <div className="card-body">
                        <div className={"addons"}>
                            {/*
                                <Checkbox/>
                                <EditButton/>
                                <DeleteButton/>
                            */}
                        </div>
                        <h5 className="card-title ">Pc</h5>
                        <p className="card-text ">R$ 250.00</p>
                        <ProgressBar now={50}  label={'50%'} title={`${50}% concluída`} />
                    </div>
                </div>
            </div>
        </CardContainer>
    )
}

function Checkbox(){
    return(
        <Form.Group >
            <Form.Check type="checkbox" className="checkbox"/>
        </Form.Group>
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
