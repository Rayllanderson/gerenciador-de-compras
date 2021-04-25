import {createContext, ReactNode, useCallback, useState} from 'react';
import {Pagination, PaginationData} from "../interfaces/paginationData";
import {createAnEmptyPagination} from "../utils/paginationUtil";

interface PaginationProviderProps {
    children: ReactNode;
}

interface PaginationContextData {
    pagination: PaginationData,
    setPage: (controller: Pagination, page: number) => void,
    getPage: (controller: Pagination) => void,
    size: number,
    setSize: (size: number) => void,
}

export const PaginationContext = createContext<PaginationContextData>({} as PaginationContextData);

export function PaginationProvider({children}: PaginationProviderProps) {

    const [pagination, setPagination] = useState<PaginationData>(createAnEmptyPagination());
    const [size, setSize] = useState<number>(20);

    const getPage = useCallback(async (controller: Pagination) => {
        await controller.getAllPageable(0, size).then((response) => {
            setPagination(response.data)
        }).catch(err => console.log(err))
    }, [size])

    const setPage = useCallback(async (controller: Pagination, page: number) => {
        await (page)
        controller.getAllPageable(page).then((response) => {
            setPagination(response.data)
        }).catch(err => console.log(err))
    }, [])

    return (
        <PaginationContext.Provider value={{pagination, setPage, getPage, size, setSize}}>
            {children}
        </PaginationContext.Provider>
    )
}

