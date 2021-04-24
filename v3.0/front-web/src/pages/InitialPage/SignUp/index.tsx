import {FiEdit2, FiLock, FiUser} from 'react-icons/fi';
import {Container} from "../styles";
import {InputWithIcon} from "../../../components/Inputs";
import {Button} from "../../../components/Buttons/InitialPage";


export function RegisterPage() {
    return (
        <Container className='inputs'>
            <div className='form-group formGroup'>
                <InputWithIcon type='text' placeholder='Nome (Opcional)'
                               icon={FiEdit2}
                               value={''} required={false}/>
            </div>
            <div className='form-group formGroup'>
                <InputWithIcon type='text' placeholder='Username'
                               icon={FiUser}
                               value={''} required={true}/>
            </div>
            <div className='form-group formGroup'>
                <InputWithIcon type='password' placeholder='Password'
                               icon={FiLock}
                               value={''} required={true}/>
            </div>
            <div className='loginButton d-grid gap-2'>
                <Button>Registrar</Button>
            </div>
        </Container>
    );
}