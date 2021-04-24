import React, {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {AuthContext} from "./AuthContext";
import {ToastContext} from "./ToastContext";
import { useHistory } from 'react-router-dom';

interface RegisterContextProviderProps {
    children: ReactNode;
}

interface RegisterContextContextData {
    name: string,
    username: string,
    password: string,
    register: () => void,
    handleUsernameChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handlePasswordChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handleNameChange: (e: React.ChangeEvent<HTMLInputElement>) => void,

}

export const RegisterContext = createContext<RegisterContextContextData>({} as RegisterContextContextData);

export function RegisterProvider({ children }: RegisterContextProviderProps) {

    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [name, setName] = useState<string>('');

    const { signUp } = useContext(AuthContext);
    const {addToast} = useContext(ToastContext);

    const history = useHistory();

    const register = useCallback(async () => {
        //validar campos
        await signUp({name, username, password }).then(() => {
            history.push('/')
            addToast({
                type: 'success',
                title: "Cadastro realizado com sucesso!",
                description: "Você já pode realizar o seu login na aplicação.",
            });
        }).catch(err => {
            console.log(err.response.data)
            addToast({
                type: 'error',
                title: 'Erro',
                description: err.response.data.fields, //tratar erros depois
            });
        })
    }, [name, username, password, signUp])

    const handleUsernameChange = useCallback( (e:  React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value)
    }, [username])

    const handlePasswordChange = useCallback( (e:  React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value)
    }, [password])

    const handleNameChange = useCallback( (e:  React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value)
    }, [name])

    return (
        <RegisterContext.Provider value={{
            username, password, name,
            register,
            handlePasswordChange, handleUsernameChange, handleNameChange,
        }}>
            {children}
        </RegisterContext.Provider>
    )
}
