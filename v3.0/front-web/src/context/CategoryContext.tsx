import React, {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {CategoryPutBody, CategoryResponseBody} from "../interfaces/categoryInterface";
import CategoryController from "../controllers/categoryController";
import {getNumberWithoutMask} from "../validations/inputValidation";
import {validateEdit, validateSave} from "../validations/categoryValidation";
import {ToastContext} from "./ToastContext";
import {PaginationContext} from "./PaginationContext";
import {ModalContext} from "./ModalContext";
import {AlertContext} from "./AlertContext";

interface CategoryProviderProps {
    children: ReactNode;
}

interface CategoryContextData {
    categories: CategoryResponseBody[],
    name: string,
    budget: string,
    action: string,
    selectedCategory: CategoryResponseBody,
    getCategoriesPageable: () => void,
    handleNameChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handleBudgetChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    submit: () => void,
    setToSave: () => void,
    setToEdit: (categoryToBeEdited: CategoryResponseBody) => void,
}

export const CategoryContext = createContext<CategoryContextData>({} as CategoryContextData);

export function CategoryProvider({children}: CategoryProviderProps) {

    const {addToast} = useContext(ToastContext);
    const {loadPage} = useContext(PaginationContext);
    const {openAddModal, closeAddModal} = useContext(ModalContext);
    const {addAlert, closeAlert} = useContext(AlertContext);

    const [categories, setCategories] = useState<CategoryResponseBody[]>([]);
    const [selectedCategory, setSelectedCategory] = useState<CategoryPutBody>({} as CategoryPutBody);
    const [name, setName] = useState<string>('');
    const [budget, setBudget] = useState<string>('');
    const [action, setAction] = useState<'save' | 'edit'>('save');

    const getCategoriesPageable = useCallback(async () => {
        await new CategoryController().findAll()
            .then((response) => {
                setCategories(response.data.content);
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
        setSelectedCategory({} as CategoryPutBody);
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
        const categoryToBeEdited = {
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
            }).catch(err => console.log(err))
        }).catch(err => {
            addAlert(err.message);
        })
    }, [budget, name, loadPage, closeAddModal, addToast, clearInputs, addAlert])

    const submit = useCallback(() => {
        if (action === 'save') save();
        if (action === 'edit') edit();
    }, [action, save])

    return (
        <CategoryContext.Provider value={{
            categories, name, budget,
            getCategoriesPageable,
            handleBudgetChange,
            handleNameChange,
            setToSave,
            setToEdit,
            selectedCategory,
            submit,
            action
        }}>
            {children}
        </CategoryContext.Provider>
    )
}

