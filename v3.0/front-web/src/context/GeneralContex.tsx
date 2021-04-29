import React, {createContext, ReactNode, useCallback, useContext} from 'react';
import {PaginationContext} from "./PaginationContext";
import {VisibilityCardItemContext} from "./CardItemVisibilityContext";
import {SelectedItemsContext} from "./SelectedItemsContext";

interface GeneralContextProviderProps {
    children: ReactNode;
}

interface GeneralContextContextData {
    clearPreviousData: () => void;
}

export const GeneralContext = createContext<GeneralContextContextData>({} as GeneralContextContextData);

export function GeneralProvider({children}: GeneralContextProviderProps) {

    const {setType, setSearch} = useContext(PaginationContext);
    const {hideCheckBox, hideDeleteButton, hideEditButton} = useContext(VisibilityCardItemContext);
    const {removeAllSelectedItems} = useContext(SelectedItemsContext);
    
    const clearPreviousData = useCallback(() => {
        setType('all');
        hideDeleteButton();
        hideEditButton();
        hideCheckBox();
        removeAllSelectedItems();
        setSearch('');
    }, [setType, hideEditButton, hideDeleteButton, removeAllSelectedItems, hideCheckBox, setSearch])

    return (
        <GeneralContext.Provider value={{
            clearPreviousData
        }}>
            {children}
        </GeneralContext.Provider>
    )
}
