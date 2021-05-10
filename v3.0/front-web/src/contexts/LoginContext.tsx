import React, {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {AuthContext} from "./AuthContext";
import {ToastContext} from "./ToastContext";
import {validateLogin} from "../validations/userValidation";
import {UserLoginBody} from "../interfaces/userInterface";
import {getValidationError} from "../utils/handleApiErros";

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

    const login = useCallback( () => {
        const user: UserLoginBody = {
            username: username,
            password: password
        }
        validateLogin(user).then(async () => {
            await signIn({username, password}).then(() => {
                addToast({
                    type: 'success',
                    title: 'Bem vindo!',
                    description: "Login realizado com sucesso!",
                });
            }).catch(err => {
                addToast({
                    type: 'error',
                    title: 'Erro',
                    description: getValidationError(err),
                });
            })
        }).catch(err => {
            addToast({
                type: 'error',
                title: 'Erro',
                description: err.message,
            });
        });

    }, [username, password, signIn, addToast])

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
