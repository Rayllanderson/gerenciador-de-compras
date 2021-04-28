import React, {createContext, ReactNode, useCallback, useState} from 'react';
import {CategoryResponseBody} from "../interfaces/categoryInterface";
import CategoryController from "../controllers/categoryController";
import {getNumberWithoutMask} from "../validations/inputValidation";

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

    const save = useCallback(() => {
        //validar campos, transformar o mask do input, mandar pra api, retornar resultado com alert no modal
    },[budget, name])

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

