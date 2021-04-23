
import {createContext, ReactNode, useState} from 'react';

interface SelectedItemsContextProviderProps {
    children: ReactNode;
}

interface SelectedItemsContextContextData {
    selectedItems: any[],
    addItemToArray: (value: any) => void,
    removeItemFromArray: (value: any) => void,
    handleCheckBoxChange: (e: any) => void,

}

/**
 * Trata das AÇÕES dos items selecionados. Tais como:
 * Array de items selecionados, adicionar ao array, remover,
 * Conexões com a api: copiar, mover, deletar o array de items selecionados.
 */
export const CardItemActionContext = createContext<SelectedItemsContextContextData>({} as SelectedItemsContextContextData);

export function CardItemActionProvider({ children }: SelectedItemsContextProviderProps) {

    const [selectedItems, setSelectedItems] = useState<any[]>([]);

    const addItemToArray = (value:any) => {
        setSelectedItems((item => [...item, value]))
    }

    const removeItemFromArray = (value:any) => {
        setSelectedItems(item => item.filter(item => item !== value));
    }

    const handleCheckBoxChange= (e: any) => {
        const value = e.target.value;
        if (e.target.checked) {
            addItemToArray(value);
        }else{
            removeItemFromArray(value);
        }
    }

    return (
        <CardItemActionContext.Provider value={{
            selectedItems, addItemToArray, removeItemFromArray, handleCheckBoxChange
        }}>
            {children}
        </CardItemActionContext.Provider>
    )
}

