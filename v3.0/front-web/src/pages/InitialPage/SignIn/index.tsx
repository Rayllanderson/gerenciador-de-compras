import {Container} from "../styles";
import {InputWithIcon} from "../../../components/Inputs";
import {FiLock, FiUser} from "react-icons/all";
import {Button} from "../../../components/Buttons/InitialPage";
import {useContext} from "react";
import {LoginContext} from "../../../context/LoginContext";

export default function LoginPage() {
    const {login, handleUsernameChange, handlePasswordChange, username, password} = useContext(LoginContext);
    return (
        <div className={'container'} style={{maxWidth: '750px'}}>
            <Container className='inputs'>
                <div className='form-group formGroup'>
                    <InputWithIcon placeholder='Username' onChange={handleUsernameChange}
                                   value={username} icon={FiUser} required type={'text'}/>
                </div>
                <div className='form-group formGroup'>
                    <InputWithIcon type='password' placeholder='Password' onChange={handlePasswordChange}
                                   value={password} icon={FiLock} required/>
                </div>
                <div className='loginButton d-grid gap-2'>
                    <Button onClick={login}>Login</Button>
                </div>
            </Container>
        </div>
    )
}