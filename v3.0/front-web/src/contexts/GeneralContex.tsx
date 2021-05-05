import React, {createContext, ReactNode, useCallback, useContext} from 'react';
import {DEFAULT_NUMBER_OF_PAGE, PaginationContext} from "./PaginationContext";
import {VisibilityCardItemContext} from "./CardItemVisibilityContext";
import {SelectedItemsContext} from "./SelectedItemsContext";

interface GeneralContextProviderProps {
    children: ReactNode;
}

interface GeneralContextContextData {
    clearPreviousData: () => void;
    clearPaginationSettings: () => void;
}

export const GeneralContext = createContext<GeneralContextContextData>({} as GeneralContextContextData);

export function GeneralProvider({children}: GeneralContextProviderProps) {

    const {setSearchType, setSearch, setSort, setSize, setOrder} = useContext(PaginationContext);
    const {hideCheckBox, hideDeleteButton, hideEditButton} = useContext(VisibilityCardItemContext);
    const {clearSelectedItems} = useContext(SelectedItemsContext);
    
    const clearPreviousData = useCallback(() => {
        setSearchType('all');
        hideDeleteButton();
        hideEditButton();
        hideCheckBox();
        clearSelectedItems();
        setSearch('');
    }, [setSearchType, hideEditButton, hideDeleteButton, clearSelectedItems, hideCheckBox, setSearch])

    const clearPaginationSettings = useCallback(() => {
        setSort('');
        setSize(DEFAULT_NUMBER_OF_PAGE);
        setOrder('asc')
    }, [setSort, setSize, setOrder])

    return (
        <GeneralContext.Provider value={{
            clearPreviousData, clearPaginationSettings
        }}>
            {children}
        </GeneralContext.Provider>
    )
}
