import {CardContainer} from "../styles";
import {CardAddon} from "../CardAddon";

interface Props {
    id: string,
}

export function ProductCard({id}: Props){

    return(
        <CardContainer>
            <div className="col">
                <div className="card h-50">
                    <div className="card-body">
                       <CardAddon id={id}/>


                    </div>
                </div>
            </div>
        </CardContainer>
    )
}

