import {SelectItem} from "./selectItemInterface";

export interface TransferProduct {
    selectItems: SelectItem[];
    currentCategoryId: string,
    newCategoryId: string
}

export interface Test {
    name: string,
    action(): void;
}