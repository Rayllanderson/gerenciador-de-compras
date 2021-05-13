import React, {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {ToastContext} from "./ToastContext";
import {PaginationContext} from "./PaginationContext";
import {ModalContext} from "./ModalContext";
import {AlertContext} from "./AlertContext";
import {SelectedItemsContext} from "./SelectedItemsContext";
import {
    AllProductPostRequestBody,
    AllProductPutRequestBody,
    ProductContextInterface,
    ProductResponseBody
} from "../interfaces/productInterface";
import {assertThatNewCategoryIdIsNotEmpty, validateEdit, validateSave} from "../validations/productValidation";
import {getNumberWithoutMask} from "../validations/inputValidation";
import {getError, getValidationError} from "../utils/handleApiErros";
import {LoadingContext} from "./LoadingContex";
import AllProductController from "../controllers/allProductController";
import {TransferAllProductRequestBody} from "../interfaces/selectItemInterface";

interface AllProductProviderProps {
    children: ReactNode;
}

export interface AllProductContextData extends ProductContextInterface{

}

export const AllProductContext = createContext<AllProductContextData>({} as AllProductContextData);

export function AllProductProvider({children}: AllProductProviderProps) {

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
    const {setButtonToLoad, clearButtonLoading} = useContext(LoadingContext);

    const [currentCategoryId, setCurrentCategoryId] = useState<string>('');
    const [name, setName] = useState<string>('');
    const [stipulatedPrice, setStipulatedPrice] = useState<string>('0');
    const [spentPrice, setSpentPrice] = useState<string>('0');
    const [isPurchased, setIsPurchased] = useState<boolean>(false);
    const [action, setAction] = useState<'save' | 'edit'>('save');
    const [selectedProduct, setSelectedProduct] = useState<ProductResponseBody>({} as ProductResponseBody);
    const [newCategoryId, setNewCategoryId] = useState<string>('');
    const [updateStatistic, setUpdateStatistic] = useState<boolean>(true);

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

    const handleNewCategoryIdChange = useCallback((e: any) => {
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
        setNewCategoryId('');
    }, [])

    const clearSelectedProduct = useCallback(() => setSelectedProduct({} as ProductResponseBody), [])

    const setToSave = useCallback(() => {
        closeAlert();
        clearInputs();
        openAddModal();
        setAction('save');
        clearButtonLoading();
    }, [openAddModal, clearInputs, closeAlert, clearButtonLoading])

    const setToEdit = useCallback((productToBeEdited: ProductResponseBody) => {
        closeAlert();
        openAddModal();
        setSelectedProduct(productToBeEdited);
        setAction('edit');
        setName(productToBeEdited.name);
        setStipulatedPrice(productToBeEdited.stipulatedPrice);
        setSpentPrice(productToBeEdited.spentPrice);
        setIsPurchased(productToBeEdited.purchased);
        clearButtonLoading();
    }, [openAddModal, closeAlert, clearButtonLoading])

    const setToRemove = useCallback((productToBeRemoved: ProductResponseBody) => {
        setSelectedProduct(productToBeRemoved);
        openRemoveModal();
        clearButtonLoading();
    }, [openRemoveModal, clearButtonLoading])

    //do It After An Update
    const fetchProducts = useCallback((api: AllProductController) => {
        loadPage(api);
        setUpdateStatistic(u => !u);
    }, [loadPage])

    /* api */
    const save = useCallback(async () => {
        const productToBeSaved: AllProductPostRequestBody = {
            name: name,
            stipulatedPrice: getNumberWithoutMask(stipulatedPrice),
            spentPrice: getNumberWithoutMask(spentPrice),
            purchased: isPurchased,
            categoryId: newCategoryId
        }
        const api = new AllProductController();
        setButtonToLoad();
        await validateSave(productToBeSaved).then(() => {
            assertThatNewCategoryIdIsNotEmpty(newCategoryId).then(async () =>
                await api.post(productToBeSaved).then(() => {
                    addToast({
                        type: 'success',
                        title: 'Pronto!',
                        description: 'Produto "' + name + '" foi salvo com sucesso!'
                    })
                    fetchProducts(api);
                    closeAddModal();
                    clearInputs();
                }).catch(err => {
                    const message: string = !!getValidationError(err) ? getValidationError(err) : getError(err);
                    addAlert(message)
                })).catch(err => addAlert(err.message))
        }).catch(err => addAlert(err.message))
        clearButtonLoading();
    }, [name, spentPrice, stipulatedPrice, clearInputs, isPurchased, fetchProducts, closeAddModal, addToast,
        addAlert, setButtonToLoad, clearButtonLoading, newCategoryId])

    const edit = useCallback(async () => {
        const productToBeEdited: AllProductPutRequestBody = {
            id: selectedProduct.id,
            name: name,
            stipulatedPrice: getNumberWithoutMask(stipulatedPrice),
            spentPrice: getNumberWithoutMask(spentPrice),
            purchased: isPurchased,
            categoryId: newCategoryId
        }
        const api = new AllProductController();
        setButtonToLoad();
        await validateEdit(productToBeEdited).then(() => {
            assertThatNewCategoryIdIsNotEmpty(newCategoryId).then (async() => {
                await api.put(productToBeEdited).then(() => {
                    addToast({
                        type: 'success',
                        title: 'Pronto!',
                        description: 'Produto "' + name + '" foi editado com sucesso!'
                    })
                    fetchProducts(api);
                    clearInputs();
                    closeAddModal();
                    clearSelectedProduct();
                }).catch(err => {
                    const message: string = !!getValidationError(err) ? getValidationError(err) : getError(err);
                    addAlert(message)
                })
            }).catch(err => addAlert(err.message))
        }).catch(err => addAlert(err.message));
        clearButtonLoading();
    }, [name, newCategoryId, stipulatedPrice, spentPrice, isPurchased, fetchProducts, closeAddModal, addToast,
        addAlert, clearSelectedProduct, selectedProduct, clearInputs, clearButtonLoading, setButtonToLoad])

    const submit = useCallback(() => {
        if (action === 'save') save().then();
        if (action === 'edit') edit().then();
    }, [action, save, edit])

    const remove = useCallback(async () => {
        const api = new AllProductController();
        setButtonToLoad();
        await api.delete(selectedProduct.id).then(() => {
            addToast({
                type: 'success',
                title: 'Feito!',
                description: `O produto ${selectedProduct.name} foi excluído!`
            });
            fetchProducts(api);
            clearSelectedProduct();
        }).catch(err => addToast({
            type: 'error',
            title: 'Error',
            description: getError(err)
        }));
        clearButtonLoading();
        closeRemoveModal();
    }, [addToast, closeRemoveModal, fetchProducts, clearSelectedProduct, selectedProduct.id, selectedProduct.name,
        setButtonToLoad, clearButtonLoading])

    const copyProductsToAnotherCategory = useCallback(async () => {
        const data: TransferAllProductRequestBody = {
            selectItems: selectedItems,
            newCategoryId: newCategoryId
        }
        setButtonToLoad();
        await assertThatNewCategoryIdIsNotEmpty(data.newCategoryId).then(async () => {
            const api = new AllProductController();
            await api.copyProductsToAnotherCategory(data)
                .then(() => {
                    addToast({
                        type: 'success',
                        title: 'Copiados!',
                        description: 'Os produtos foram copiados para categoria selecionada.'
                    })
                    setNewCategoryId('');
                    clearSelectedItems();
                    fetchProducts(api);
                }).catch((err) => addToast({
                    type: 'error',
                    title: 'Error',
                    description: getError(err)
                }));
            clearButtonLoading();
            closeTransferModal();
        }).catch(err => addAlert(err.message))
    }, [addToast, closeTransferModal, clearSelectedItems, selectedItems, newCategoryId, addAlert,
        setButtonToLoad, clearButtonLoading, fetchProducts])

    const moveProductsToAnotherCategory = useCallback(async () => {
        const data: TransferAllProductRequestBody = {
            selectItems: selectedItems,
            newCategoryId: newCategoryId
        }
        setButtonToLoad();
        await assertThatNewCategoryIdIsNotEmpty(data.newCategoryId).then(async () => {
            const api = new AllProductController();
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
                    fetchProducts(api);
                }).catch((err) => addToast({
                    type: 'error',
                    title: 'Error',
                    description: getError(err)
                }));
        }).catch(err => addAlert(err.message))
        clearButtonLoading();
    }, [addToast, closeTransferModal, clearSelectedItems,
        selectedItems, newCategoryId, addAlert, fetchProducts, clearButtonLoading, setButtonToLoad])

    const removeVarious = useCallback(async () => {
        const api = new AllProductController();
        setButtonToLoad();
        await api.deleteVarious(selectedItems)
            .then(() => {
                addToast({
                    type: 'success',
                    title: 'Prontinho!',
                    description: `Os produtos selecionados foram excluídos.`
                })
                fetchProducts(api);
            }).catch((err) => addToast({
                type: 'error',
                title: 'Error',
                description: getError(err)
            }));
        clearSelectedItems();
        closeConfirmModal();
        clearButtonLoading();
    }, [selectedItems, fetchProducts, clearSelectedItems, addToast, closeConfirmModal, setButtonToLoad, clearButtonLoading])

    return (
        <AllProductContext.Provider value={{
            handleIsPurchasedChange,
            handleSpentPriceChange,
            handleStipulatedPriceChange,
            handleNameChange,
            isPurchased,
            stipulatedPrice,
            spentPrice,
            action,
            name,
            setToSave,
            currentCategoryId,
            setCurrentCategoryId,
            submit,
            setToEdit,
            remove,
            setToRemove,
            selectedProduct,
            copyProductsToAnotherCategory,
            handleNewCategoryIdChange,
            moveProductsToAnotherCategory,
            setNewCategoryId,
            removeVarious,
            updateStatistic
        }}>
            {children}
        </AllProductContext.Provider>
    )
}