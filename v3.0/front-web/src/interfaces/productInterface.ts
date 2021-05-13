interface ProductModel {
    name: string;
    stipulatedPrice: string;
    spentPrice: string;
    purchased: boolean;
}

export interface ProductPostBody extends ProductModel{

}

export interface ProductPutBody extends ProductModel{
    id: string;
}

export interface ProductResponseBody extends ProductModel{
    id: string;
}


export interface AllProductPostRequestBody extends ProductModel{
    categoryId: string;
}

export interface AllProductPutRequestBody extends ProductModel{
    id: string;
    categoryId: string;
}