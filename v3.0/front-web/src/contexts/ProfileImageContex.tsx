import React, {createContext, ReactNode, useCallback, useState} from 'react';

interface ProfileImageProviderProps {
    children: ReactNode;
}

interface ProfileImageContextData {
    cardOpacity: number,
    toggleCardVisibility: () => void,
}

export const ProfileImageContext = createContext<ProfileImageContextData>({} as ProfileImageContextData);

export function ProfileImageProvider({children}: ProfileImageProviderProps) {

    const [cardOpacity, setCardOpacity] = useState<number>(0);
    const [cardIsOpen, setCardIsOpen] = useState<boolean>(false)

    const showCard = useCallback(() => {
        setCardOpacity(1);
        setCardIsOpen(true);
    }, [setCardOpacity, setCardIsOpen]);

    const hideCard = useCallback(() => {
        setCardOpacity(0);
        setCardIsOpen(false);
    }, [setCardOpacity, setCardIsOpen]);

    const toggleCardVisibility = useCallback(() => {
        if (cardIsOpen) hideCard();
        else showCard();
    }, [showCard, hideCard, cardIsOpen]);

    return (
        <ProfileImageContext.Provider value={{
            cardOpacity, toggleCardVisibility
        }}>
            {children}
        </ProfileImageContext.Provider>
    )
}
