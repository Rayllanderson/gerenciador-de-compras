
import {createContext, ReactNode, useCallback, useState} from 'react';

interface LoadingProviderProps {
    children: ReactNode;
}

interface LoadingContextData {
    isLoading: boolean;
    btnIsLoading: boolean;
    setButtonToLoading: () => void
    clearButtonLoading: () => void
    setToLoading: () => void
    clearLoading: () => void

}

export const LoadingContext = createContext<LoadingContextData>({} as LoadingContextData);

export function LoadingProvider({ children }: LoadingProviderProps) {
    const [isLoading, setIsLoading] = useState(false);
    const [btnIsLoading, setBtnIsLoading] = useState(false);

    const setButtonToLoading = useCallback(() => {
        setBtnIsLoading(true);
    }, [])

    const clearButtonLoading = useCallback(() => {
        setBtnIsLoading(false);
    }, [])

    const setToLoading = useCallback(() => {
        setIsLoading(true)
    }, [])

    const clearLoading = useCallback(() => {
        setIsLoading(false)
    }, [])

    return (
        <LoadingContext.Provider value={{
            isLoading, btnIsLoading, setToLoading, clearLoading, clearButtonLoading, setButtonToLoading
        }}>
            {children}
        </LoadingContext.Provider>
    )
}