import LoginPage from "./SignIn";
import {LoginFooter} from "../../components/Footer/InitialPage/Login";
import {RegisterPage} from "./SignUp";
import {RegisterFooter} from "../../components/Footer/InitialPage/Register/index2";

interface Props {
    name: string;
}

export function Page({name: component}: Props) {
    return (
        <>
            {(component === 'login' || !component) &&
            <>
                <LoginPage/>
                <LoginFooter/>
            </>
            }

            {
                component === 'register' &&
                <>
                    <RegisterPage/>
                    <RegisterFooter/>
                </>
            }
        </>
    )
}