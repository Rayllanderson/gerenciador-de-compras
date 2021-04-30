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
    const {clearSelectedItems} = useContext(SelectedItemsContext);
    
    const clearPreviousData = useCallback(() => {
        setType('all');
        hideDeleteButton();
        hideEditButton();
        hideCheckBox();
        clearSelectedItems();
        setSearch('');
    }, [setType, hideEditButton, hideDeleteButton, clearSelectedItems, hideCheckBox, setSearch])

    return (
        <GeneralContext.Provider value={{
            clearPreviousData
        }}>
            {children}
        </GeneralContext.Provider>
    )
}
