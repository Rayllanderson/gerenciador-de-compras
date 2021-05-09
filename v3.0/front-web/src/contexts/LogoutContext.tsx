import React, {createContext, ReactNode, useCallback, useContext} from 'react';
import {AuthContext} from "./AuthContext";
import {ToastContext} from "./ToastContext";
import {GeneralContext} from "./GeneralContex";

interface LogoutContextProviderProps {
    children: ReactNode;
}

interface LogoutContextContextData {
    logout: () => void,
}

export const LogoutContext = createContext<LogoutContextContextData>({} as LogoutContextContextData);

export function LogoutProvider({children}: LogoutContextProviderProps) {

    const {signOut} = useContext(AuthContext);
    const {addToast} = useContext(ToastContext);
    const {clearPreviousData} = useContext(GeneralContext);

    const logout = useCallback(async () => {
        await signOut();
        clearPreviousData();
        addToast({
            type: 'info',
            title: 'Logout',
            description: "Você fez logout. Até mais!",
        })
    }, [addToast, clearPreviousData, signOut])


    return (
        <LogoutContext.Provider value={{
            logout
        }}>
            {children}
        </LogoutContext.Provider>
    )
}
