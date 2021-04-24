import Logo from "../../components/Logo";
import {LoginFooter} from "../../components/Footer/Login";
import {Container} from "./styles";
import {InputWithIcon} from "../../components/Inputs";
import {FiLock, FiUser} from "react-icons/all";
import {Button} from "../../components/Buttons/Login";
import {useContext} from "react";
import {LoginContext} from "../../context/LoginContext";

export default function LoginPage() {
    const {login, handleUsernameChange, handlePasswordChange, username, password} = useContext(LoginContext);
    return (
        <div className={'container appearFromRight'} style={{maxWidth: '750px'}}>
            <Logo/>

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

            <LoginFooter/>
        </div>
    )
}