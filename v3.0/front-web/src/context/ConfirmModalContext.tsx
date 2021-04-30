
import {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {CategoryContext} from "./CategoryContext";
import {ModalContext} from "./ModalContext";

interface ConfirmModalContextProviderProps {
    children: ReactNode;
}

interface ConfirmModalContextContextData {
    action: () => void,
    confirmModalText: string,

    duplicateCategoryAction: () => void,
}

export const ConfirmModalContext = createContext<ConfirmModalContextContextData>({} as ConfirmModalContextContextData);

export function ConfirmModalProvider({ children }: ConfirmModalContextProviderProps) {

    const [action, setAction] = useState<() => void>(() => {});
    const [confirmModalText, setConfirmModalText] = useState<string>('');
    const {openConfirmModal} = useContext(ModalContext)

    const {duplicateCategories} = useContext(CategoryContext)

    const duplicateCategoryAction = useCallback(() => {
        openConfirmModal();
        setConfirmModalText('Você tem certeza que deseja duplicar as categorias selecionadas?')
        setAction(() => duplicateCategories);
    }, [openConfirmModal, duplicateCategories]);


    return (
        <ConfirmModalContext.Provider value={{
            confirmModalText, duplicateCategoryAction, action
        }}>
            {children}
        </ConfirmModalContext.Provider>
    )
}

