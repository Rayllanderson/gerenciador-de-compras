import React from "react";

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

export interface ProductContextInterface{
    setToEdit: (product: ProductResponseBody) => void,
    setToRemove: (product: ProductResponseBody) => void,
    submit: () => void,
    remove: () => void,
    selectedProduct: ProductResponseBody,
    copyProductsToAnotherCategory: () => void,
    moveProductsToAnotherCategory: () => void,
    removeVarious: () => void,
    handleNameChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handleStipulatedPriceChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handleSpentPriceChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handleIsPurchasedChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handleNewCategoryIdChange: (e: any) => void,
    setToSave: () => void,
    setNewCategoryId: (id: string) => void,
    name: string,
    stipulatedPrice: string,
    spentPrice: string,
    isPurchased: boolean,
    updateStatistic: boolean,
    action: string,
    currentCategoryId: string,
    setCurrentCategoryId: (id: string) => void,
}