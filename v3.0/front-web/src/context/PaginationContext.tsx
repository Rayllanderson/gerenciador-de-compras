import {createContext, ReactNode, useCallback, useState} from 'react';
import {Pagination, PaginationData} from "../interfaces/paginationData";
import {createAnEmptyPagination} from "../utils/paginationUtil";

interface PaginationProviderProps {
    children: ReactNode;
}

interface PaginationContextData {
    pagination: PaginationData,
    setPage: (controller: Pagination, page: number) => void,
    loadPage: (controller: Pagination) => void,
    size: number,
    setSize: (size: number) => void,
}

export const PaginationContext = createContext<PaginationContextData>({} as PaginationContextData);

export function PaginationProvider({children}: PaginationProviderProps) {

    const DEFAULT_NUMBER_OF_PAGE: number = 6;
    const [pagination, setPagination] = useState<PaginationData>(createAnEmptyPagination());
    const [size, setSize] = useState<number>(DEFAULT_NUMBER_OF_PAGE);

    const loadPage = useCallback(async (controller: Pagination) => {
        await controller.getAllPageable(0, size).then((response) => {
            setPagination(response.data)
        }).catch(err => console.log(err))
    }, [size])

    const setPage = useCallback(async (controller: Pagination, page: number) => {
        await controller.getAllPageable(page, size).then((response) => {
            setPagination(response.data)
        }).catch(err => console.log(err))
    }, [size])

    return (
        <PaginationContext.Provider value={{pagination, setPage, loadPage, size, setSize}}>
            {children}
        </PaginationContext.Provider>
    )
}

