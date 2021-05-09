
import {createContext, ReactNode, useCallback, useState} from 'react';
import {UserResponseBody} from "../interfaces/userInterface";
import UserController from "../controllers/userController";

interface AccountContextProviderProps {
    children: ReactNode;
}

interface AccountContextContextData {
    user: UserResponseBody,
    fetchUser: () => void;
}

export const AccountContext = createContext<AccountContextContextData>({} as AccountContextContextData);

export function AccountProvider({ children }: AccountContextProviderProps) {

    const [user, setUser] = useState<UserResponseBody>({} as UserResponseBody);

    const fetchUser = useCallback(async () => {
        await new UserController().fetchUser().then((response) => {
            setUser(response.data);
        }).catch((err) => console.log(err))
    }, [setUser])

    return (
        <AccountContext.Provider value={{
            fetchUser, user
        }}>
            {children}
        </AccountContext.Provider>
    )
}

