import {FiLock, FiUser} from "react-icons/all";
import {Button} from '../../Buttons/Login';
import {InputWithIcon} from "../../Inputs";
import {Container} from './styles';
import {useContext} from "react";
import {LoginContext} from "../../../context/LoginContext";

export function LoginForm() {

    const {login, handleUsernameChange, handlePasswordChange, username, password} = useContext(LoginContext);

    return (
        <Container className='inputs'>
            <div className='form-group formGroup'>
                <InputWithIcon placeholder='Username' onChange={handleUsernameChange}
                       value={username} icon={FiUser} required={true} type={'text'}/>
            </div>
            <div className='form-group formGroup'>
                <InputWithIcon type='password' placeholder='Password' onChange={handlePasswordChange}
                       value={password} icon={FiLock} required={true}/>
            </div>
            <div className='loginButton d-grid gap-2'>
                <Button onClick={login}>Login</Button>
            </div>
        </Container>
    );
}