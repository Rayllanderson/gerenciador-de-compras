
import {createContext, ReactNode, useState} from 'react';

interface ModalContextProviderProps {
    children: ReactNode;
}

interface ModalContextContextData {
    showAddModal: boolean,
    showRemoveModal: boolean,
    openAddModal: () => void,
    closeAddModal: () => void,
    openRemoveModal: () => void,
    closeRemoveModal: () => void,
}

export const ModalContext = createContext<ModalContextContextData>({} as ModalContextContextData);

export function ModalProvider({ children }: ModalContextProviderProps) {

    const [showAddModal, setShowAddModal] = useState<boolean>(false);
    const [showRemoveModal, setShowRemoveModal] = useState<boolean>(false);

    function openAddModal() {
        setShowAddModal(true);
    }

    function closeAddModal() {
        setShowAddModal(false);
    }

    function openRemoveModal() {
        setShowRemoveModal(true);
    }

    function closeRemoveModal() {
        setShowRemoveModal(false);
    }

    return (
        <ModalContext.Provider value={{
            closeAddModal, closeRemoveModal, openAddModal,
            openRemoveModal, showRemoveModal, showAddModal
        }}>
            {children}
        </ModalContext.Provider>
    )
}

