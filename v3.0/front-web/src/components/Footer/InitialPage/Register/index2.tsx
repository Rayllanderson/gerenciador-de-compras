import {Link} from "react-router-dom";
import {FiArrowLeft} from "react-icons/fi";
import {Container} from '../styles';

export function RegisterFooter() {
    return (
        <Container>
            <div className='d-flex justify-content-center'>
                <Link to="/"><FiArrowLeft/> Voltar para o login</Link>
            </div>
        </Container>
    )
}