
import {createContext, ReactNode, useState} from 'react';

interface CardActionsContextProviderProps {
    children: ReactNode;
}

interface CardActionsContextContextData {
    checkBoxIsVisible: boolean,
    showCheckBox: () => void,
    hideCheckBox: () => void,
    editButtonIsVisible: boolean,
    deleteButtonIsVisible: boolean,
    hideEditButton: () => void,
    showEditButton: () => void,
    hideDeleteButton: () => void,
    showDeleteButton: () => void,

}

export const CardActionsContext = createContext<CardActionsContextContextData>({} as CardActionsContextContextData);

export function CardActionsContextProvider({ children }: CardActionsContextProviderProps) {

    const [checkBoxIsVisible, setCheckBoxVisibility] = useState(false);
    const [editButtonIsVisible, setEditButtonVisibility] = useState(false);
    const [deleteButtonIsVisible, setDeleteButtonVisibility] = useState(false);

    function showCheckBox () {
        setCheckBoxVisibility(true);
    }

    function hideCheckBox () {
        setCheckBoxVisibility(false);
    }

    function hideEditButton () {
        setEditButtonVisibility(false);
    }
    function showEditButton () {
        setEditButtonVisibility(true);
    }

    function hideDeleteButton () {
        setDeleteButtonVisibility(false);
    }
    function showDeleteButton () {
        setDeleteButtonVisibility(true);
    }


    return (
        <CardActionsContext.Provider value={{
            checkBoxIsVisible, showCheckBox, hideCheckBox,
            editButtonIsVisible, showEditButton, hideEditButton,
            deleteButtonIsVisible, showDeleteButton, hideDeleteButton,
        }}>
            {children}
        </CardActionsContext.Provider>
    )
}

