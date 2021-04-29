import React, {createContext, ReactNode, useCallback, useState} from 'react';
import {Page, Pageable} from "../interfaces/page";
import {createAnEmptyPagination} from "../utils/paginationUtil";

interface PaginationProviderProps {
    children: ReactNode;
}

interface PaginationContextData {
    pagination: Page,
    setPage: (controller: Pageable, page: number) => void,
    loadPage: (controller: Pageable) => void,
    setPagination: (page: Page) => void,
    size: number,
    setSize: (size: number) => void,
    setType: (type: 'search' | 'all') => void,
    handleSearchChange: (e: any) => void,
    setSearch: (search: string) => void,
    search: string
}

export const PaginationContext = createContext<PaginationContextData>({} as PaginationContextData);

export function PaginationProvider({children}: PaginationProviderProps) {

    const DEFAULT_NUMBER_OF_PAGE: number = 3;
    const [pagination, setPagination] = useState<Page>(createAnEmptyPagination());
    const [size, setSize] = useState<number>(DEFAULT_NUMBER_OF_PAGE);
    const [type, setType] = useState<'search' | 'all'>('all');
    const [search, setSearch] = useState('');

    const loadPage = useCallback(async (controller: Pageable) => {
        if (type === 'all') {
            await controller.getAllPageable(0, size).then((response) => {
                setPagination(response.data)
            }).catch(err => console.log(err))
        }
        if (type === 'search') {
            await controller.findByName(search as string, 0, size).then((response) => {
                setPagination(response.data);
            }).catch(err => console.log(err))
        }
    }, [size, type, search])

    const setPage = useCallback(async (controller: Pageable, page: number) => {
        if (type === 'all') {
            await controller.getAllPageable(page, size).then((response) => {
                setPagination(response.data);
            }).catch(err => console.log(err))
        }
        if (type === 'search') {
            await controller.findByName(search, page, size).then((response) => {
                setPagination(response.data);
            }).catch(err => console.log(err))
        }
    }, [size, type, search])

    const handleSearchChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setType("search");
        setSearch(e.target.value);
        if (e.target.value.length === 0) setType('all');
    }, [setType])

    return (
        <PaginationContext.Provider value={{pagination, setPagination, setPage, loadPage, size, setSize, setType, handleSearchChange,
            setSearch, search}}>
            {children}
        </PaginationContext.Provider>
    )
}

