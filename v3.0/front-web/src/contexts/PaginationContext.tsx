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
    setSearchType: (type: 'search' | 'all') => void,
    handleSearchChange: (e: any) => void,
    handleSortChange: (e: any) => void,
    handleSizeChange: (e: any) => void,
    handleOrderChange: (e: any) => void,
    setSearch: (search: string) => void,
    search: string,
    sort: string,
    order: 'asc' | 'desc',
    setSort: (sortBy: string) => void,
    setOrder: (value: 'asc' | 'desc') => void,
}

export const PaginationContext = createContext<PaginationContextData>({} as PaginationContextData);

export function PaginationProvider({children}: PaginationProviderProps) {

    const [pagination, setPagination] = useState<Page>(createAnEmptyPagination());
    const [size, setSize] = useState<number>(DEFAULT_NUMBER_OF_PAGE);
    const [sort, setSort] = useState<string>('');
    const [order, setOrder] = useState<'asc' | 'desc'>('asc');

    const [searchType, setSearchType] = useState<'search' | 'all'>('all');
    const [search, setSearch] = useState('');

    const loadPage = useCallback(async (controller: Pageable) => {
        if (searchType === 'all') {
            await controller.getAllPageable(0, sort, order, size).then((response) => {
                setPagination(response.data)
            }).catch(err => console.log(err))
        }
        if (searchType === 'search') {
            await controller.findByName(search as string, 0, size).then((response) => {
                setPagination(response.data);
            }).catch(err => console.log(err))
        }
    }, [size, searchType, search, sort, order])

    const setPage = useCallback(async (controller: Pageable, page: number) => {
        if (searchType === 'all') {
            await controller.getAllPageable(page, sort, order, size).then((response) => {
                setPagination(response.data);
            }).catch(err => console.log(err))
        }
        if (searchType === 'search') {
            await controller.findByName(search, page, size).then((response) => {
                setPagination(response.data);
            }).catch(err => console.log(err))
        }
    }, [size, searchType, search, sort, order])

    const handleSearchChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setSearchType("search");
        setSearch(e.target.value);
        if (e.target.value.length === 0) setSearchType('all');
    }, [setSearchType])

    const handleSortChange = useCallback((e: any) => {
        setSort(e.target.value)
    }, [])

    const handleSizeChange = useCallback((e: any) => {
        setSize(e.target.value)
    }, [])

    const handleOrderChange = useCallback((e: any) => {
        setOrder(e.target.value)
    }, [])

    return (
        <PaginationContext.Provider value={{pagination, setPagination, setPage, loadPage, size, setSize,
            sort, setSort, handleSortChange, handleSizeChange,
            handleOrderChange, order, setOrder,
            setSearchType, handleSearchChange,
            setSearch, search}}>
            {children}
        </PaginationContext.Provider>
    )
}

export const DEFAULT_NUMBER_OF_PAGE: number = 6;

