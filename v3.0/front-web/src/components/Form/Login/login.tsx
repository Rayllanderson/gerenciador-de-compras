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