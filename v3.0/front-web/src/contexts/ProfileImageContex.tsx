import React, {createContext, ReactNode, useCallback, useState} from 'react';

interface ProfileImageProviderProps {
    children: ReactNode;
}

interface ProfileImageContextData {
    cardDisplay: 'none' | 'block',
    toggleCardVisibility: () => void,
}

export const ProfileImageContext = createContext<ProfileImageContextData>({} as ProfileImageContextData);

export function ProfileImageProvider({children}: ProfileImageProviderProps) {

    const [cardDisplay, setCardOpacity] = useState<'none' | 'block'>('none');
    const [cardIsOpen, setCardIsOpen] = useState<boolean>(false)

    const showCard = useCallback(() => {
        setCardOpacity("block");
        setCardIsOpen(true);
    }, [setCardOpacity, setCardIsOpen]);

    const hideCard = useCallback(() => {
        setCardOpacity('none');
        setCardIsOpen(false);
    }, [setCardOpacity, setCardIsOpen]);

    const toggleCardVisibility = useCallback(() => {
        if (cardIsOpen) hideCard();
        else showCard();
    }, [showCard, hideCard, cardIsOpen]);

    return (
        <ProfileImageContext.Provider value={{
            cardDisplay, toggleCardVisibility
        }}>
            {children}
        </ProfileImageContext.Provider>
    )
}
