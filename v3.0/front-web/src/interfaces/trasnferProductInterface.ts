import {SelectItem} from "./selectItemInterface";

export interface TransferProduct {
    selectItems: SelectItem[];
    currentCategoryId: string,
    newCategoryId: string
}