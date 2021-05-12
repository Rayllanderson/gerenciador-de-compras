import {FiX} from "react-icons/all";
import {Container} from './styles'

interface Props {
    className?: string,
    onClick?: (e: any) => void
}

export function CloseButton({className, onClick}: Props){
    return(
        <Container>
            <button
                onClick={onClick}
                className={'badge close-card' + className}>
                <FiX size={23}/>
            </button>
        </Container>
    )
}