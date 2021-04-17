import {Container} from "./styles";
import {PrimaryButton} from '../../Buttons/styles';
import {FiMoreVertical, FiPlus} from "react-icons/all";

export function ButtonGroup (){
    return (
        <Container className="container">
            <div>
                <PrimaryButton className={"btn"} style={{borderBottomRightRadius: 0, borderTopRightRadius: 0, color: 'var(--green)'}}>
                    <FiPlus size={21}/>
                </PrimaryButton>

                <PrimaryButton className={"btn"} style={{borderBottomLeftRadius: 0, borderTopLeftRadius: 0}}>
                    <FiMoreVertical size={21}/>
                </PrimaryButton>
                </div>
        </Container>
    )
}