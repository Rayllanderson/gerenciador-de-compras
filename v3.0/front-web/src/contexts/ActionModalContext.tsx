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
    filterType: 'product' | 'category',

    duplicateCategoryAction: () => void,
    removeVariousCategoriesAction: () => void,
    removeVariousProductsAction: () => void,
    copyProductsAction: () => void
    moveProductsAction: () => void
    closeTransferModalAction: () => void
    openFilterCategoryModalAction: () => void
    openFilterProductModalAction: () => void
    closeFilterModalAction: () => void
}

export const ActionModalContext = createContext<ConfirmModalContextContextData>({} as ConfirmModalContextContextData);

export function ConfirmModalProvider({ children }: ConfirmModalContextProviderProps) {

    const [action, setAction] = useState<() => void>(() => {});
    const [confirmModalText, setConfirmModalText] = useState<string>('');
    const [transferModalTitle, setTransferModalTitle] = useState<'Copiar' | 'Mover'>('Copiar');
    const [filterType, setFilterType] = useState<'product' | 'category'>('category');

    const {openConfirmModal, openTransferModal, closeTransferModal, openFilterModal, closeFilterModal} = useContext(ModalContext);
    const {duplicateCategories, removeVarious} = useContext(CategoryContext);
    const {removeVarious: removeVariousProducts, setNewCategoryId} = useContext(ProductContext);

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

    const removeVariousProductsAction = useCallback(() => {
        openConfirmModal();
        setConfirmModalText('Você tem certeza que deseja remover permanentemente os produtos selecionadas?')
        setAction(() => removeVariousProducts);
    }, [openConfirmModal, removeVariousProducts]);

    const copyProductsAction = useCallback(() => {
        openTransferModal();
        setTransferModalTitle('Copiar');
    } ,[openTransferModal])

    const moveProductsAction = useCallback(() => {
        openTransferModal();
        setTransferModalTitle('Mover');
    } ,[openTransferModal])

    const closeTransferModalAction = useCallback(() => {
        setNewCategoryId('');
        closeTransferModal();
    },[setNewCategoryId, closeTransferModal])

    const openFilterCategoryModalAction = useCallback(() => {
        setFilterType('category');
        openFilterModal();
    }, [openFilterModal]);

    const openFilterProductModalAction = useCallback(() => {
        setFilterType('product');
        openFilterModal();
    }, [openFilterModal]);

    const closeFilterModalAction = useCallback(() => {
        closeFilterModal();
        //setar tudo pro default...
    }, [openFilterModal]);


    return (
        <ActionModalContext.Provider value={{
            confirmModalText, duplicateCategoryAction, action, removeVariousCategoriesAction,
            copyProductsAction, transferModalTitle, moveProductsAction, removeVariousProductsAction,
            closeTransferModalAction, openFilterCategoryModalAction, openFilterProductModalAction, filterType,
            closeFilterModalAction
        }}>
            {children}
        </ActionModalContext.Provider>
    )
}

