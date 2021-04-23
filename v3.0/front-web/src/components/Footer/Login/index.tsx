import {Link} from 'react-router-dom'
import {FiLogIn} from "react-icons/all";
import {Container} from './styles';
export function LoginFooter() {
    return (
        <Container >
            <div className="link-register d-flex justify-content-center">
                <Link to="/register"><FiLogIn/> Criar Conta</Link>
            </div>
        </Container>
    )
}