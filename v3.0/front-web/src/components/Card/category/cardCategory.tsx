import {ProgressBar} from "react-bootstrap";
import {CardContainer} from './styles'

export function CardCategory(){
    return(
        <CardContainer>
            <div className="col">
                <div className="card h-50">
                    <div className="card-body">
                        <h5 className="card-title ">Pc</h5>
                        <p className="card-text ">R$ 250.00</p>
                        <ProgressBar striped  now={50}  label={'50%'} title={`${50}% concluída`} />
                    </div>
                </div>
            </div>
        </CardContainer>
    )
}