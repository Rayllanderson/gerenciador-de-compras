interface CategoryModel {
    name: string;
    budget: string;
}

export interface CategoryPostBody extends CategoryModel {
}

export interface CategoryPutBody extends CategoryModel {
    id: string;
}

export interface CategoryResponseBody extends CategoryModel{
    id: string;
}
