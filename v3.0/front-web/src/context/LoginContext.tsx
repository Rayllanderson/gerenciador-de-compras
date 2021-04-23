import {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {Link} from 'react-router-dom';

interface LoginContextProviderProps {
    children: ReactNode;
}

interface LoginContextContextData {

}

export const LoginContext = createContext<LoginContextContextData>({} as LoginContextContextData);

export function LoginContextProvider({ children }: LoginContextProviderProps) {

    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');

    const handleSubmit = useCallback(() => {
        //validar campos
    }, [])



    return (
        <LoginContext.Provider value={{
        }}>
            {children}
        </LoginContext.Provider>
    )
}
