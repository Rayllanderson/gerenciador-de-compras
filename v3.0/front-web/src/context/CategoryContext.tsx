import {createContext, ReactNode, useCallback, useState} from 'react';
import {CategoryResponseBody} from "../interfaces/categoryInterface";
import CategoryController from "../controllers/categoryController";

interface CategoryProviderProps {
    children: ReactNode;
}

interface CategoryContextData {
    categories: CategoryResponseBody[],
    getCategoriesPageable: () => void,
}

export const CategoryContext = createContext<CategoryContextData>({} as CategoryContextData);

export function CategoryProvider({children}: CategoryProviderProps) {

    const [categories, setCategories] = useState<CategoryResponseBody[]>([]);

    const getCategoriesPageable = useCallback(async () => {
        await new CategoryController().findAll()
            .then((response) => {
                setCategories(response.data.content);
            }).catch(err => console.log(err))
    }, [])


    return (
        <CategoryContext.Provider value={{categories, getCategoriesPageable}}>
            {children}
        </CategoryContext.Provider>
    )
}

