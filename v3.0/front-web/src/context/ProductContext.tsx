import {createContext, ReactNode, useCallback, useState} from 'react';
import ProductController from "../controllers/categoryController";
import {ProductResponseBody} from "../interfaces/productInterface";

interface ProductProviderProps {
    children: ReactNode;
}

interface ProductContextData {
    products: ProductResponseBody[],
    loadProductsPageable: () => void,
}

export const ProductContext = createContext<ProductContextData>({} as ProductContextData);

export function ProductProvider({children}: ProductProviderProps) {

    const [products, setProducts] = useState<ProductResponseBody[]>([]);

    const loadProductsPageable = useCallback(async () => {
        await new ProductController().findAll()
            .then((response) => {
                setProducts(response.data.content);
            }).catch(err => console.log(err))
    }, [])


    return (
        <ProductContext.Provider value={{products, loadProductsPageable}}>
            {children}
        </ProductContext.Provider>
    )
}

