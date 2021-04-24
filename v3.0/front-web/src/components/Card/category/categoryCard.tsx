import {ProgressBar} from "react-bootstrap";
import {CategoryCardContainer} from '../styles'
import {CardAddon} from "../CardAddon";

interface Props {
    id: string,
}

export function CategoryCard({id}: Props){


    return(
        <CategoryCardContainer>
            <a href={'/products'}>
            <div className="col">
                <div className="card h-50">
                    <div className="card-body">
                        <CardAddon id={id}/>
                        <h5 className="card-title ">Supermercado</h5>
                        <p className="card-text ">R$ 250.00</p>
                        <ProgressBar now={50}  label={'50%'} title={`${50}% concluída`} />
                    </div>
                </div>
            </div>
            </a>
        </CategoryCardContainer>
    )
}
