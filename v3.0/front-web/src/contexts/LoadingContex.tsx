
import {createContext, ReactNode, useCallback, useState} from 'react';

interface LoadingProviderProps {
    children: ReactNode;
}

interface LoadingContextData {
    isLoading: boolean;
    btnIsLoading: boolean;
    setButtonToLoad: () => void
    clearButtonLoading: () => void
    setToLoad: () => void
    clearLoading: () => void

}

export const LoadingContext = createContext<LoadingContextData>({} as LoadingContextData);

export function LoadingProvider({ children }: LoadingProviderProps) {
    const [isLoading, setIsLoading] = useState(false);
    const [btnIsLoading, setBtnIsLoading] = useState(false);

    const setButtonToLoad = useCallback(() => {
        setBtnIsLoading(true);
    }, [])

    const clearButtonLoading = useCallback(() => {
        setBtnIsLoading(false);
    }, [])

    const setToLoad = useCallback(() => {
        setIsLoading(true)
    }, [])

    const clearLoading = useCallback(() => {
        setIsLoading(false)
    }, [])

    return (
        <LoadingContext.Provider value={{
            isLoading, btnIsLoading, setToLoad, clearLoading, clearButtonLoading, setButtonToLoad
        }}>
            {children}
        </LoadingContext.Provider>
    )
}