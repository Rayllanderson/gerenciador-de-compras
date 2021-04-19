
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

export const VisibilityCardItemContext = createContext<CardActionsContextContextData>({} as CardActionsContextContextData);

/**
 * Trata a visibilidade dos botões de selecionar items, editar, deletar no Card.
 */
export function VisibilityCardItemContextProvider({ children }: CardActionsContextProviderProps) {

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
        <VisibilityCardItemContext.Provider value={{
            checkBoxIsVisible, showCheckBox, hideCheckBox,
            editButtonIsVisible, showEditButton, hideEditButton,
            deleteButtonIsVisible, showDeleteButton, hideDeleteButton,
        }}>
            {children}
        </VisibilityCardItemContext.Provider>
    )
}

