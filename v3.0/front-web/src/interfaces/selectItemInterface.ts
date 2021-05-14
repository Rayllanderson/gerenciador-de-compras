export interface SelectItem {
    id: string,
    name: string,
    isSelected: boolean;
}

export interface TransferAllProductRequestBody {
    selectItems: SelectItem[],
    newCategoryId: string
}