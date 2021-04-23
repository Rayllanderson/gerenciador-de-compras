import {LoginForm} from "../../components/Form/Login/login";
import Logo from "../../components/Logo";
import {LoginFooter} from "../../components/Footer/Login";

export default function LoginPage(){
    return(
        <div className={'container appearFromRight'} style={{maxWidth: '750px'}}>
            <Logo/>
            <LoginForm/>
            <LoginFooter/>
        </div>
    )
}