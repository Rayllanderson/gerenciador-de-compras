import React, {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {AuthContext} from "./AuthContext";
import {ToastContext} from "./ToastContext";
import {validateLogin} from "../validations/userValidation";
import {UserLoginBody} from "../interfaces/userInterface";
import {getError, getValidationError} from "../utils/handleApiErros";
import {LoadingContext} from "./LoadingContex";

interface LoginContextProviderProps {
    children: ReactNode;
}

interface LoginContextContextData {
    username: string,
    password: string,
    login: () => void,
    handleUsernameChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handlePasswordChange: (e: React.ChangeEvent<HTMLInputElement>) => void,

}

export const LoginContext = createContext<LoginContextContextData>({} as LoginContextContextData);

export function LoginProvider({children}: LoginContextProviderProps) {

    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');

    const {signIn} = useContext(AuthContext);
    const {addToast} = useContext(ToastContext);
    const {setButtonToLoad, clearButtonLoading} = useContext(LoadingContext);

    const login = useCallback( async () => {
        const user: UserLoginBody = {
            username: username,
            password: password
        }
        setButtonToLoad();
       await validateLogin(user).then(async () => {
            await signIn({username, password}).then(() => {
                addToast({
                    type: 'success',
                    title: 'Bem vindo!',
                    description: "Login realizado com sucesso!",
                });
            }).catch(err => {
                const message:string = !!getValidationError(err) ? getValidationError(err) : getError(err);
                addToast({
                    type: 'error',
                    title: 'Erro',
                    description: message,
                });
            })
        }).catch(err => {
            addToast({
                type: 'error',
                title: 'Erro',
                description: err.message,
            });
        });
        clearButtonLoading();
    }, [username, password, signIn, addToast, setButtonToLoad, clearButtonLoading])

    const handleUsernameChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value)
    }, [])

    const handlePasswordChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value)
    }, [])

    return (
        <LoginContext.Provider value={{
            username, password,
            login, handlePasswordChange, handleUsernameChange
        }}>
            {children}
        </LoginContext.Provider>
    )
}
