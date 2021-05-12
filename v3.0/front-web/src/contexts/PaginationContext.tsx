import React, {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {Page, Pageable, PageType} from "../interfaces/page";
import {createAnEmptyPagination} from "../utils/paginationUtil";
import {getError} from "../utils/handleApiErros";
import {ToastContext} from "./ToastContext";
import {LoadingContext} from "./LoadingContex";

interface PaginationProviderProps {
    children: ReactNode;
}

export const DEFAULT_NUMBER_OF_PAGE: number = 6;

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
    handlePageTypeChange: (value: any) => void,
    setPageType: (value: PageType) => void,
    pageType: PageType
}

export const PaginationContext = createContext<PaginationContextData>({} as PaginationContextData);

export function PaginationProvider({children}: PaginationProviderProps) {

    const [pagination, setPagination] = useState<Page>(createAnEmptyPagination());
    const [size, setSize] = useState<number>(DEFAULT_NUMBER_OF_PAGE);
    const [sort, setSort] = useState<string>('');
    const [order, setOrder] = useState<'asc' | 'desc'>('asc');
    const [pageType, setPageType] = useState<PageType>({type: 'all'});

    const [searchType, setSearchType] = useState<'search' | 'all'>('all');
    const [search, setSearch] = useState('');

    const {addToast} = useContext(ToastContext);
    const {setToLoad, clearLoading} = useContext(LoadingContext);

    const loadPage = useCallback(async (controller: Pageable) => {
        setToLoad();
        if (searchType === 'all') {
            await controller.getPageable(0, sort, order, pageType, size).then((response) => {
                setPagination(response.data)
            }).catch(err => addToast({
                type: 'error',
                title: 'Error',
                description: getError(err)
            }));
        }
        if (searchType === 'search') {
            await controller.findByName(search as string, 0, size).then((response) => {
                setPagination(response.data);
            }).catch(err => addToast({
                type: 'error',
                title: 'Error',
                description: getError(err)
            }));
        }
        clearLoading();
    }, [size, searchType, search, sort, order, addToast, setToLoad, clearLoading, pageType])

    const setPage = useCallback(async (controller: Pageable, page: number) => {
        setToLoad();
        if (searchType === 'all') {
            await controller.getPageable(page, sort, order, pageType, size).then((response) => {
                setPagination(response.data);
            }).catch(err => addToast({
                type: 'error',
                title: 'Error',
                description: getError(err)
            }));
        }
        if (searchType === 'search') {
            await controller.findByName(search, page, size).then((response) => {
                setPagination(response.data);
            }).catch(err => addToast({
                type: 'error',
                title: 'Error',
                description: getError(err)
            }));
        }
        clearLoading();
    }, [size, searchType, search, sort, order, addToast, setToLoad, clearLoading, pageType])

    const handleSearchChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setSearchType("search");
        setSearch(e.target.value);
        if (e.target.value.length === 0) {
            setSearchType('all');
            setPageType({type: 'all'});
        }
    }, [setSearchType, setPageType])

    const handleSortChange = useCallback((e: any) => {
        setSort(e.target.value)
    }, [])

    const handleSizeChange = useCallback((e: any) => {
        setSize(e.target.value)
    }, [])

    const handleOrderChange = useCallback((e: any) => {
        setOrder(e.target.value)
    }, [])

    const handlePageTypeChange = useCallback((e: any) => {
        setPageType({type: e.target.value})
    }, [])

    return (
        <PaginationContext.Provider value={{pagination, setPagination, setPage, loadPage, size, setSize,
            sort, setSort, handleSortChange, handleSizeChange,
            handleOrderChange, order, setOrder,
            setSearchType, handleSearchChange, handlePageTypeChange,
            setSearch, search, setPageType, pageType}}>
            {children}
        </PaginationContext.Provider>
    )
}