import {createContext, ReactNode, useCallback, useState} from 'react';
import {CategoryResponseBody} from "../interfaces/categoryInterface";
import CategoryController from "../controllers/categoryController";
import {Pagination} from "../interfaces/pagination";
import {createAnEmptyPagination} from "../utils/paginationUtil";

interface CategoryProviderProps {
    children: ReactNode;
}

interface CategoryContextData {
    categories: CategoryResponseBody[],
    pagination: Pagination,
    getCategoriesPageable: () => void,
    setPage: (page: number) => void
}

export const CategoryContext = createContext<CategoryContextData>({} as CategoryContextData);

export function CategoryProvider({children}: CategoryProviderProps) {

    const [categories, setCategories] = useState<CategoryResponseBody[]>([]);
    const [pagination, setPagination] = useState<Pagination>(createAnEmptyPagination());
    const getCategoriesPageable = useCallback(async () => {
        await new CategoryController().findAll()
            .then((response) => {
                setPagination(response.data)
                setCategories(response.data.content);
            }).catch(err => console.log(err))
    }, [])

    const setPage = useCallback(async (page:number) => {
        await new CategoryController().findAllPageable(3, page)
            .then((response) => {
                setPagination(response.data)
                setCategories(response.data.content);
            }).catch(err => console.log(err))
    }, [])


    return (
        <CategoryContext.Provider value={{categories, getCategoriesPageable, pagination, setPage}}>
            {children}
        </CategoryContext.Provider>
    )
}

