import React, {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {CategoryPutBody, CategoryResponseBody} from "../interfaces/categoryInterface";
import CategoryController from "../controllers/categoryController";
import {getNumberWithoutMask} from "../validations/inputValidation";
import {validateEdit, validateSave} from "../validations/categoryValidation";
import {ToastContext} from "./ToastContext";
import {PaginationContext} from "./PaginationContext";
import {ModalContext} from "./ModalContext";
import {AlertContext} from "./AlertContext";
import {SelectedItemsContext} from "./SelectedItemsContext";
import {SelectItem} from "../interfaces/selectItemInterface";
import {toTransferCategoryRequestBody} from "../utils/selectItemUtil";

interface CategoryProviderProps {
    children: ReactNode;
}

interface CategoryContextData {
    categories: CategoryResponseBody[],
    setCategories: (categories: CategoryResponseBody[]) => void
    name: string,
    budget: string,
    action: string,
    selectedCategory: CategoryResponseBody,
    loadCategoriesNonPageable: () => void,
    handleNameChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handleBudgetChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    submit: () => void,
    remove: () => void,
    setToSave: () => void,
    setToEdit: (categoryToBeEdited: CategoryResponseBody) => void,
    setToRemove: (categoryToBeEdited: CategoryResponseBody) => void,
    duplicateCategories: () => void,
    removeVarious: () => void,
}

export const CategoryContext = createContext<CategoryContextData>({} as CategoryContextData);

export function CategoryProvider({children}: CategoryProviderProps) {

    const {addToast} = useContext(ToastContext);
    const {loadPage} = useContext(PaginationContext);
    const {
        openAddModal,
        closeAddModal,
        openRemoveModal,
        closeRemoveModal,
        closeConfirmModal
    } = useContext(ModalContext);
    const {addAlert, closeAlert} = useContext(AlertContext);
    const {selectedItems, clearSelectedItems} = useContext(SelectedItemsContext);

    const [categories, setCategories] = useState<CategoryResponseBody[]>([]);
    const [selectedCategory, setSelectedCategory] = useState<CategoryResponseBody>({} as CategoryResponseBody);
    const [name, setName] = useState<string>('');
    const [budget, setBudget] = useState<string>('');
    const [action, setAction] = useState<'save' | 'edit'>('save');

    const loadCategoriesNonPageable = useCallback(async () => {
        await new CategoryController().findAllNonPageable()
            .then((response) => {
                setCategories(response.data);
            }).catch(err => console.log(err))
    }, [])

    const handleNameChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    }, [])

    const handleBudgetChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setBudget(e.target.value);
    }, [])

    const clearInputs = useCallback(() => {
        setName('');
        setBudget('');
    }, [])

    const setToSave = useCallback(() => {
        closeAlert();
        clearInputs();
        setSelectedCategory({} as CategoryResponseBody);
        openAddModal();
        setAction('save');
    }, [openAddModal, clearInputs, closeAlert])

    const setToEdit = useCallback((categoryToBeEdited: CategoryResponseBody) => {
        closeAlert();
        setSelectedCategory(categoryToBeEdited);
        openAddModal();
        setAction('edit');
        setName(categoryToBeEdited.name);
        setBudget(categoryToBeEdited.budget);
    }, [openAddModal, closeAlert])

    const setToRemove = useCallback((categoryToBeRemoved: CategoryResponseBody) => {
        setSelectedCategory(categoryToBeRemoved);
        openRemoveModal();
    }, [openRemoveModal])

    const clearSelectedCategory = useCallback(() => setSelectedCategory({} as CategoryResponseBody), [])

    const save = useCallback(() => {
        const categoryToBeSaved = {
            name: name,
            budget: getNumberWithoutMask(budget)
        }
        validateSave(categoryToBeSaved).then(async () => {
            const api = new CategoryController();
            await api.post(categoryToBeSaved).then(() => {
                addToast({
                    type: 'success',
                    title: 'Pronto!',
                    description: 'Lista "' + name + '" foi salva com sucesso!'
                })
                loadPage(api);
                closeAddModal();
                clearInputs();
            }).catch(err => console.log(err))
        }).catch(err => {
            addAlert(err.message);
        })
    }, [budget, name, loadPage, closeAddModal, addToast, clearInputs, addAlert])

    const edit = useCallback(() => {
        const categoryToBeEdited: CategoryPutBody = {
            id: selectedCategory.id,
            name: name,
            budget: getNumberWithoutMask(budget)
        }
        validateEdit(categoryToBeEdited).then(async () => {
            const api = new CategoryController();
            await api.put(categoryToBeEdited).then(() => {
                addToast({
                    type: 'success',
                    title: 'Pronto!',
                    description: 'Lista "' + name + '" foi editada com sucesso!'
                })
                loadPage(api);
                closeAddModal();
                clearInputs();
                clearSelectedCategory();
            }).catch(err => console.log(err))
        }).catch(err => {
            addAlert(err.message);
        })
    }, [budget, name, loadPage, closeAddModal, addToast, clearInputs, addAlert, clearSelectedCategory, selectedCategory.id])

    const submit = useCallback(() => {
        if (action === 'save') save();
        if (action === 'edit') edit();
    }, [action, save, edit])

    const remove = useCallback(async () => {
        const api = new CategoryController();
        await api.delete(selectedCategory.id).then(() => {
            addToast({
                type: 'success',
                title: 'Feito!',
                description: `A lista ${selectedCategory.name} foi excluída!`
            });
            closeRemoveModal();
            loadPage(api);
            clearSelectedCategory();
        })
    }, [addToast, closeRemoveModal, loadPage, clearSelectedCategory, selectedCategory.id, selectedCategory.name])

    const duplicateCategories = useCallback(() => {
        const api = new CategoryController();
        selectedItems.forEach(async (item: SelectItem) => {
            await api.duplicateCategory(toTransferCategoryRequestBody(item))
                .then(() => {
                    addToast({
                        type: 'success',
                        title: 'Feito!',
                        description: `Lista ${item.name} duplicada`
                    })
                    loadPage(api);
                }).catch((err) => console.log(err.response.data.message));
        });
        clearSelectedItems();
        closeConfirmModal();
    }, [selectedItems, loadPage, clearSelectedItems, addToast, closeConfirmModal])

    const removeVarious = useCallback(async () => {
        const api = new CategoryController();
        await api.deleteVarious(selectedItems)
            .then(() => {
                addToast({
                    type: 'success',
                    title: 'Prontinho!',
                    description: `As listas selecionadas foram excluídas.`
                })
                loadPage(api);
            }).catch((err) => console.log(err.response.data.message));
        clearSelectedItems();
        closeConfirmModal();
    }, [selectedItems, loadPage, clearSelectedItems, addToast, closeConfirmModal])

    return (
        <CategoryContext.Provider value={{
            categories, name, budget,
            loadCategoriesNonPageable,
            handleBudgetChange,
            handleNameChange,
            setToSave,
            setToEdit,
            selectedCategory,
            submit,
            action,
            remove, setToRemove,
            duplicateCategories,
            removeVarious,
            setCategories
        }}>
            {children}
        </CategoryContext.Provider>
    )
}

