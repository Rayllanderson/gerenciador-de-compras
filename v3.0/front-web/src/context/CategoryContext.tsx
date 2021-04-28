import React, {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {CategoryResponseBody} from "../interfaces/categoryInterface";
import CategoryController from "../controllers/categoryController";
import {getNumberWithoutMask} from "../validations/inputValidation";
import {validateSave} from "../validations/categoryValidation";
import {ToastContext} from "./ToastContext";
import {PaginationContext} from "./PaginationContext";
import {ModalContext} from "./ModalContext";

interface CategoryProviderProps {
    children: ReactNode;
}

interface CategoryContextData {
    categories: CategoryResponseBody[],
    name:string,
    budget: string,
    getCategoriesPageable: () => void,
    handleNameChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handleBudgetChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    save: () => void,
}

export const CategoryContext = createContext<CategoryContextData>({} as CategoryContextData);

export function CategoryProvider({children}: CategoryProviderProps) {

    const [categories, setCategories] = useState<CategoryResponseBody[]>([]);
    const {addToast} = useContext(ToastContext);
    const {loadPage} = useContext(PaginationContext);
    const {closeAddModal} = useContext(ModalContext);

    const [name, setName] = useState<string>('');
    const [budget, setBudget] = useState<string>('');

    const getCategoriesPageable = useCallback(async () => {
        await new CategoryController().findAll()
            .then((response) => {
                setCategories(response.data.content);
            }).catch(err => console.log(err))
    }, [])

    const handleNameChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    },[])

    const handleBudgetChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setBudget(e.target.value);
    },[])

    const clearInputs = useCallback(() => {
        setName('');
        setBudget('');
    }, [])


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
            }).catch((err) => {
                // lançar alert no modal aqui...
            })
        })
    },[budget, name, loadPage, closeAddModal, addToast, clearInputs])

    return (
        <CategoryContext.Provider value={{
            categories, name, budget,
            getCategoriesPageable,
            handleBudgetChange,
            handleNameChange,
            save
        }}>
            {children}
        </CategoryContext.Provider>
    )
}

