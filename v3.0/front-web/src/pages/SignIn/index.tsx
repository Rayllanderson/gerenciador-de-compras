import {LoginForm} from "../../components/Form/Login/login";
import Logo from "../../components/Logo";

export default function LoginPage(){
    return(
        <div className={'container'} style={{maxWidth: '750px'}}>
            <Logo/>
            <LoginForm/>
        </div>
    )
}