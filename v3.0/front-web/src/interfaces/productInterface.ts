interface ProductModel {
    name: string;
    stipulatedPrice: number;
    spentPrice: number;
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