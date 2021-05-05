
import {createContext, ReactNode, useState} from 'react';

interface ModalContextProviderProps {
    children: ReactNode;
}

interface ModalContextContextData {
    showAddModal: boolean,
    showRemoveModal: boolean,
    showConfirmModal: boolean,
    showTransferModal: boolean,
    showFilterModal: boolean,
    openAddModal: () => void,
    closeAddModal: () => void,
    openRemoveModal: () => void,
    closeRemoveModal: () => void,
    openConfirmModal: () => void,
    closeConfirmModal: () => void,
    openTransferModal: () => void,
    closeTransferModal: () => void,
    openFilterModal: () => void,
    closeFilterModal: () => void,
}

export const ModalContext = createContext<ModalContextContextData>({} as ModalContextContextData);

export function ModalProvider({ children }: ModalContextProviderProps) {

    const [showAddModal, setShowAddModal] = useState<boolean>(false);
    const [showRemoveModal, setShowRemoveModal] = useState<boolean>(false);
    const [showConfirmModal, setShowConfirmModal] = useState<boolean>(false);
    const [showTransferModal, setShowTransferModal] = useState<boolean>(false);
    const [showFilterModal, setShowFilterModal] = useState<boolean>(false);

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

    function openConfirmModal() {
        setShowConfirmModal(true);
    }

    function closeConfirmModal() {
        setShowConfirmModal(false);
    }

    function openTransferModal() {
        setShowTransferModal(true);
    }

    function closeTransferModal() {
        setShowTransferModal(false);
    }

    function openFilterModal() {
        setShowFilterModal(true);
    }

    function closeFilterModal() {
        setShowFilterModal(false);
    }

    return (
        <ModalContext.Provider value={{
            closeAddModal, closeRemoveModal, openAddModal,
            openRemoveModal, showRemoveModal, showAddModal,
            closeConfirmModal, openConfirmModal, showConfirmModal,
            closeTransferModal, openTransferModal, showTransferModal,
            closeFilterModal, openFilterModal, showFilterModal
        }}>
            {children}
        </ModalContext.Provider>
    )
}

