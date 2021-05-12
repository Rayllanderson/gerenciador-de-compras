import {SelectItem} from "../interfaces/selectItemInterface";
import {TransferCategoryRequestBody} from "../interfaces/categoryInterface";

export function createAnEmptySelectedItem():SelectItem {
    return {id: '', name: '', isSelected: false}
}


export function toTransferCategoryRequestBody(selectedItem: SelectItem) : TransferCategoryRequestBody{
    return {id: selectedItem.id, newName: selectedItem.name + ' (cópia)'} as TransferCategoryRequestBody
}