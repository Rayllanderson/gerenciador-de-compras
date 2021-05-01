
import {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {CategoryContext} from "./CategoryContext";
import {ModalContext} from "./ModalContext";
import {ProductContext} from "./ProductContext";

interface ConfirmModalContextProviderProps {
    children: ReactNode;
}

interface ConfirmModalContextContextData {
    action: () => void,
    confirmModalText: string,
    transferModalTitle: 'Copiar' | 'Mover',

    duplicateCategoryAction: () => void,
    removeVariousCategoriesAction: () => void,
    copyProductsAction: () => void
    moveProductsAction: () => void
}

export const ActionModalContext = createContext<ConfirmModalContextContextData>({} as ConfirmModalContextContextData);

export function ConfirmModalProvider({ children }: ConfirmModalContextProviderProps) {

    const [action, setAction] = useState<() => void>(() => {});
    const [confirmModalText, setConfirmModalText] = useState<string>('');
    const [transferModalTitle, setTransferModalTitle] = useState<'Copiar' | 'Mover'>('Copiar');

    const {openConfirmModal, openTransferModal} = useContext(ModalContext);
    const {duplicateCategories, removeVarious} = useContext(CategoryContext)
    const {copyProductsToAnotherCategory, moveProductsToAnotherCategory} = useContext(ProductContext);

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

    const copyProductsAction = useCallback(() => {
        openTransferModal();
        setAction(() => copyProductsToAnotherCategory);
        setTransferModalTitle('Copiar');
    } ,[openTransferModal, copyProductsToAnotherCategory])

    const moveProductsAction = useCallback(() => {
        openTransferModal();
        setAction(() => moveProductsToAnotherCategory);
        setTransferModalTitle('Mover');
    } ,[openTransferModal, moveProductsToAnotherCategory])

    return (
        <ActionModalContext.Provider value={{
            confirmModalText, duplicateCategoryAction, action, removeVariousCategoriesAction,
            copyProductsAction, transferModalTitle, moveProductsAction
        }}>
            {children}
        </ActionModalContext.Provider>
    )
}

