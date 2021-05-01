import React, {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {ToastContext} from "./ToastContext";
import {PaginationContext} from "./PaginationContext";
import {ModalContext} from "./ModalContext";
import {AlertContext} from "./AlertContext";
import {SelectedItemsContext} from "./SelectedItemsContext";
import ProductController from "../controllers/productController";
import {ProductPostBody, ProductPutBody, ProductResponseBody} from "../interfaces/productInterface";
import {validateEdit, validateSave} from "../validations/productValidation";
import {getNumberWithoutMask} from "../validations/inputValidation";
import {TransferProduct} from "../interfaces/trasnferProductInterface";

interface ProductProviderProps {
    children: ReactNode;
}

interface ProductContextData {
    name: string,
    stipulatedPrice: string,
    spentPrice: string,
    isPurchased: boolean,
    action: string,
    currentCategoryId: string,
    setCurrentCategoryId: (id: string) => void,
    handleNameChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handleStipulatedPriceChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handleSpentPriceChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handleIsPurchasedChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handleNewCategoryIdChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    setToSave: () => void,
    setNewCategoryId: (id: string) => void,
    setToEdit: (product: ProductResponseBody) => void,
    setToRemove: (product: ProductResponseBody) => void,
    submit: () => void,
    remove: () => void,
    selectedProduct: ProductResponseBody,
    copyProductsToAnotherCategory: () => void,
    moveProductsToAnotherCategory: () => void,
    removeVarious: () => void,
}

export const ProductContext = createContext<ProductContextData>({} as ProductContextData);

export function ProductProvider({children}: ProductProviderProps) {

    const {addToast} = useContext(ToastContext);
    const {loadPage} = useContext(PaginationContext);
    const {
        openAddModal,
        closeAddModal,
        openRemoveModal,
        closeRemoveModal,
        closeConfirmModal,
        closeTransferModal
    } = useContext(ModalContext);
    const {addAlert, closeAlert} = useContext(AlertContext);
    const {selectedItems, clearSelectedItems} = useContext(SelectedItemsContext);

    const [currentCategoryId, setCurrentCategoryId] = useState<string>('');
    const [name, setName] = useState<string>('');
    const [stipulatedPrice, setStipulatedPrice] = useState<string>('0');
    const [spentPrice, setSpentPrice] = useState<string>('0');
    const [isPurchased, setIsPurchased] = useState<boolean>(false);
    const [action, setAction] = useState<'save' | 'edit'>('save');
    const [selectedProduct, setSelectedProduct] = useState<ProductResponseBody>({} as ProductResponseBody);
    const [newCategoryId, setNewCategoryId] = useState<string>('');

    /*Handle change functions*/
    const handleNameChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    }, [])

    const handleStipulatedPriceChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setStipulatedPrice(e.target.value);
    }, [])

    const handleSpentPriceChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setSpentPrice(e.target.value);
    }, [])

    const handleNewCategoryIdChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setNewCategoryId(e.target.value);
    }, [])

    const handleIsPurchasedChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        const isChecked = e.target.checked;
        setIsPurchased(isChecked);
        const isNotChecked = !isChecked
        if (isNotChecked) setSpentPrice('0');
    }, [])

    /* utils */
    const clearInputs = useCallback(() => {
        setName('');
        setStipulatedPrice('');
        setSpentPrice('');
        setIsPurchased(false);
    }, [])

    const clearSelectedProduct = useCallback(() => setSelectedProduct({} as ProductResponseBody), [])

    const setToSave = useCallback(() => {
        closeAlert();
        clearInputs();
        openAddModal();
        setAction('save');
    }, [openAddModal, clearInputs, closeAlert])

    const setToEdit = useCallback((productToBeEdited: ProductResponseBody) => {
        closeAlert();
        openAddModal();
        setSelectedProduct(productToBeEdited);
        setAction('edit');
        setName(productToBeEdited.name);
        setStipulatedPrice(productToBeEdited.stipulatedPrice);
        setSpentPrice(productToBeEdited.spentPrice);
        setIsPurchased(productToBeEdited.purchased);
    }, [openAddModal, closeAlert])

    const setToRemove = useCallback((productToBeRemoved: ProductResponseBody) => {
        setSelectedProduct(productToBeRemoved);
        openRemoveModal();
    }, [openRemoveModal])

    /* api */
    const save = useCallback(() => {
        const productToBeSaved: ProductPostBody = {
            name: name,
            stipulatedPrice: getNumberWithoutMask(stipulatedPrice),
            spentPrice: getNumberWithoutMask(spentPrice),
            purchased: isPurchased
        }
        const api = new ProductController(currentCategoryId);
        validateSave(productToBeSaved).then(async () => {
            await api.post(productToBeSaved).then(() => {
                addToast({
                    type: 'success',
                    title: 'Pronto!',
                    description: 'Produto "' + name + '" foi salvo com sucesso!'
                })
                loadPage(api);
                closeAddModal();
                clearInputs();
            }).catch(err => console.log(err.response))//erro que vem da api
        }).catch(err => addAlert(err.message));
    }, [name, spentPrice, stipulatedPrice, isPurchased, currentCategoryId, loadPage, closeAddModal, addToast,
        clearInputs, addAlert])

    const edit = useCallback(() => {
        const productToBeEdited: ProductPutBody = {
            id: selectedProduct.id,
            name: name,
            stipulatedPrice: getNumberWithoutMask(stipulatedPrice),
            spentPrice: getNumberWithoutMask(spentPrice),
            purchased: isPurchased
        }
        const api = new ProductController(currentCategoryId);
        validateEdit(productToBeEdited).then(async () => {
            await api.put(productToBeEdited).then(() => {
                addToast({
                    type: 'success',
                    title: 'Pronto!',
                    description: 'Produto "' + name + '" foi editado com sucesso!'
                })
                loadPage(api);
                closeAddModal();
                clearInputs();
                clearSelectedProduct();
            }).catch(err => console.log(err.response))//erro que vem da api
        }).catch(err => addAlert(err.message));
    }, [name, currentCategoryId, stipulatedPrice, spentPrice, isPurchased, loadPage, closeAddModal, addToast,
        clearInputs, addAlert, clearSelectedProduct, selectedProduct])

    const submit = useCallback(() => {
        if (action === 'save') save();
        if (action === 'edit') edit();
    }, [action, save, edit])


    const remove = useCallback(async () => {
        const api = new ProductController(currentCategoryId);
        await api.delete(selectedProduct.id).then(() => {
            addToast({
                type: 'success',
                title: 'Feito!',
                description: `A lista ${selectedProduct.name} foi excluído!`
            });
            closeRemoveModal();
            loadPage(api);
            clearSelectedProduct();
        })
    }, [addToast, closeRemoveModal, loadPage, clearSelectedProduct, selectedProduct.id, selectedProduct.name, currentCategoryId])

    const copyProductsToAnotherCategory = useCallback(async () => {
        const data: TransferProduct = {
            selectItems: selectedItems,
            currentCategoryId: currentCategoryId,
            newCategoryId: newCategoryId
        }
        await new ProductController(currentCategoryId).copyProductsToAnotherCategory(data)
            .then(() => {
                addToast({
                    type: 'success',
                    title: 'Copiados!',
                    description: 'Os produtos foram copiados para categoria selecionada.'
                })
                setNewCategoryId('');
                closeTransferModal();
                clearSelectedItems();
            })
    }, [addToast, closeTransferModal, clearSelectedItems, selectedItems, currentCategoryId, newCategoryId])

    const moveProductsToAnotherCategory = useCallback(async () => {
        const data: TransferProduct = {
            selectItems: selectedItems,
            currentCategoryId: currentCategoryId,
            newCategoryId: newCategoryId
        }
        const api = new ProductController(currentCategoryId);
        await api.moveProductsToAnotherCategory(data)
            .then(() => {
                addToast({
                    type: 'success',
                    title: 'Movidos!',
                    description: 'Os produtos foram movidos para categoria selecionada.'
                })
                setNewCategoryId('');
                closeTransferModal();
                clearSelectedItems();
                loadPage(api)
            })
    }, [addToast, closeTransferModal, clearSelectedItems, selectedItems, currentCategoryId, newCategoryId, loadPage])

    const removeVarious = useCallback(async () => {
        const api = new ProductController(currentCategoryId);
        await api.deleteVarious(selectedItems)
            .then(() => {
                addToast({
                    type: 'success',
                    title: 'Prontinho!',
                    description: `Os produtos selecionados foram excluídos.`
                })
                loadPage(api);
            }).catch((err) => console.log(err.response.data.message));
        clearSelectedItems();
        closeConfirmModal();
    }, [selectedItems, loadPage, clearSelectedItems, addToast, closeConfirmModal, currentCategoryId])

    return (
        <ProductContext.Provider value={{
            handleIsPurchasedChange, handleSpentPriceChange, handleStipulatedPriceChange, handleNameChange,
            isPurchased, stipulatedPrice, spentPrice, action, name, setToSave, currentCategoryId, setCurrentCategoryId,
            submit, setToEdit, remove, setToRemove, selectedProduct, copyProductsToAnotherCategory, handleNewCategoryIdChange,
            moveProductsToAnotherCategory, setNewCategoryId, removeVarious
        }}>
            {children}
        </ProductContext.Provider>
    )
}

