import {FiX} from "react-icons/all";
import {Container} from './styles'

export function CloseButton(){
    return(
        <Container>
            <button
                className="badge close-card">
                <FiX size={23}/>
            </button>
        </Container>
    )
}