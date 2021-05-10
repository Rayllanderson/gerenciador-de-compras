
import {createContext, ReactNode, useState} from 'react';

interface AlertContextProviderProps {
    children: ReactNode;
}

interface AlertContextContextData {
    show: boolean,
    message: string,
    addAlert: (message: string) => void,
    closeAlert: () => void,
}

export const AlertContext = createContext<AlertContextContextData>({} as AlertContextContextData);

export function AlertProvider({ children }: AlertContextProviderProps) {

    const [show, setShow] = useState<boolean>(false);
    const [message, setMessage] = useState<string>('');

    function addAlert(message: string) {
        setShow(true);
        setMessage(message);
        setTimeout(() => {
            setShow(false)
        }, 3500)
    }

    function closeAlert() {
        setShow(false);
        setMessage('');
    }

    return (
        <AlertContext.Provider value={{
            closeAlert, addAlert, show, message
        }}>
            {children}
        </AlertContext.Provider>
    )
}

