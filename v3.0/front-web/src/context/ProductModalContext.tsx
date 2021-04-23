
import {createContext, ReactNode, useState} from 'react';

interface ProductModalContextProviderProps {
    children: ReactNode;
}

interface ProductModalContextContextData {
    checked: boolean,
    handleCheckBoxChange: (e: any) => void,
}

/**
 * Trata dos states dos inputs do modal. Ou seja, funções de manipular valores estarão aqui, tais como:
 * Function de handleChange de input, function de handleChange de checkbox, values, e outras funções em geral.
 */
export const ProductModalContext = createContext<ProductModalContextContextData>({} as ProductModalContextContextData);

export function ProductModalProvider({ children }: ProductModalContextProviderProps) {

    const [checked, setChecked] = useState<boolean>(false)

    const handleCheckBoxChange= (e: any) => {
        if (e.target.checked) {
            setChecked(true);
        }else{
            setChecked(false);
        }
    }

    return (
        <ProductModalContext.Provider value={{
            checked, handleCheckBoxChange
        }}>
            {children}
        </ProductModalContext.Provider>
    )
}

