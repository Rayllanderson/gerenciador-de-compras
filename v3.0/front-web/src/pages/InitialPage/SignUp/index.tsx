import {FiEdit2, FiLock, FiUser} from 'react-icons/fi';
import {Container} from "../styles";
import {InputWithIcon} from "../../../components/Inputs";
import {Button} from "../../../components/Buttons/InitialPage";
import {useContext} from "react";
import {RegisterContext} from "../../../context/RegisterContext";


export function RegisterPage() {

    const {register, handleNameChange, handlePasswordChange, handleUsernameChange,
    name, username, password} = useContext(RegisterContext);

    return (
        <Container className='inputs'>
            <div className='form-group formGroup'>
                <InputWithIcon type='text' placeholder='Nome (Opcional)'
                               icon={FiEdit2}
                               onChange={handleNameChange}
                               value={name} required={false}/>
            </div>
            <div className='form-group formGroup'>
                <InputWithIcon type='text' placeholder='Username'
                               icon={FiUser}
                               onChange={handleUsernameChange}
                               value={username} required/>
            </div>
            <div className='form-group formGroup'>
                <InputWithIcon type='password' placeholder='Password'
                               icon={FiLock}
                               onChange={handlePasswordChange}
                               value={password} required/>
            </div>
            <div className='loginButton d-grid gap-2'>
                <Button onClick={register}>Registrar</Button>
            </div>
        </Container>
    );
}