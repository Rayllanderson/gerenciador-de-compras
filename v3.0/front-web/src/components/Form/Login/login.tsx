import {FiLock, FiUser} from "react-icons/all";
import {Button} from '../../Buttons/Login';
import {InputWithIcon} from "../../Inputs";
import {Container} from './styles';

export function LoginForm() {
    return (
        <Container className='inputs'>
            <div className='form-group formGroup'>
                <InputWithIcon placeholder='Username'
                       icon={FiUser} required={true} type={'text'}/>
            </div>
            <div className='form-group formGroup'>
                <InputWithIcon type='password' placeholder='Password'
                       icon={FiLock}
                        required={true}/>
            </div>
            <div className='loginButton d-grid gap-2'>
                <Button>Login</Button>
            </div>
        </Container>
    );
}

/*
export function LoginFooter() {
    return (
        <div className='links'>
            <div className='d-flex justify-content-center'>
                <Link to="/forget-password">Esqueci minha senha</Link>
            </div>
            <div className="link-register d-flex justify-content-center">
                <Link to="/register"><FiLogIn/> &nbsp; Criar Conta</Link>
            </div>
        </div>
    )
}
 */