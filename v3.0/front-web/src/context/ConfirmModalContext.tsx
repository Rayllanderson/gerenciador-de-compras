
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
    removeVariousCategoriesAction: () => void,
}

export const ConfirmModalContext = createContext<ConfirmModalContextContextData>({} as ConfirmModalContextContextData);

export function ConfirmModalProvider({ children }: ConfirmModalContextProviderProps) {

    const [action, setAction] = useState<() => void>(() => {});
    const [confirmModalText, setConfirmModalText] = useState<string>('');
    const {openConfirmModal} = useContext(ModalContext)

    const {duplicateCategories, removeVarious} = useContext(CategoryContext)

    const duplicateCategoryAction = useCallback(() => {
        openConfirmModal();
        setConfirmModalText('Você tem certeza que deseja duplicar as listas selecionadas?')
        setAction(() => duplicateCategories);
    }, [openConfirmModal, duplicateCategories]);

    const removeVariousCategoriesAction = useCallback(() => {
        openConfirmModal();
        setConfirmModalText('Você tem certeza que deseja remover permanentemente as listas selecionadas?')
        setAction(() => removeVarious);
    }, [openConfirmModal, removeVarious]);


    return (
        <ConfirmModalContext.Provider value={{
            confirmModalText, duplicateCategoryAction, action, removeVariousCategoriesAction
        }}>
            {children}
        </ConfirmModalContext.Provider>
    )
}

