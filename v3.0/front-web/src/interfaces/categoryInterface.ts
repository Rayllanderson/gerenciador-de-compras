interface CategoryModel {
    name: string;
    budget: number;
}

export interface CategoryPostBody extends CategoryModel {
}

export interface CategoryPutBody extends CategoryModel {
    id: string;
}

export interface CategoryResponseBody extends CategoryModel{
    id: string;
}