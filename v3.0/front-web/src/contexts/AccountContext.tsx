import {createContext, ReactNode, useCallback, useState} from 'react';
import {UserResponseBody} from "../interfaces/userInterface";
import UserController from "../controllers/userController";

interface AccountContextProviderProps {
    children: ReactNode;
}

interface AccountContextContextData {
    user: UserResponseBody,
    name: string,
    username: string,
    password: string,
    fetchUser: () => void;
    setName: (value: string) => void;
    setUsername: (value: string) => void;
    handleNameChange: (e:any) => void;
    handleUsernameChange: (e:any) => void;
    handlePasswordChange: (e:any) => void;
}

export const AccountContext = createContext<AccountContextContextData>({} as AccountContextContextData);

export function AccountProvider({ children }: AccountContextProviderProps) {

    const [user, setUser] = useState<UserResponseBody>({} as UserResponseBody);
    const [name, setName] = useState<string>('')
    const [username, setUsername] = useState<string>('')
    const [password, setPassword] = useState<string>('')

    const fetchUser = useCallback(async () => {
        await new UserController().fetchUser().then((response) => {
            setUser(response.data);
        }).catch((err) => console.log(err))
    }, [setUser])

    const handleNameChange = useCallback((e: any) => {
        setName(e.target.value);
    }, [])

    const handleUsernameChange = useCallback((e: any) => {
        setUsername(e.target.value);
    }, [])

    const handlePasswordChange = useCallback((e: any) => {
        setPassword(e.target.value);
    }, [])

    return (
        <AccountContext.Provider value={{
            fetchUser, user, handleUsernameChange, handleNameChange, name, username, setUsername, setName,
            handlePasswordChange, password
        }}>
            {children}
        </AccountContext.Provider>
    )
}

