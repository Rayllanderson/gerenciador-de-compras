import {SelectItem} from "../interfaces/selectItemInterface";

export function createAnEmptySelectedItem():SelectItem {
    return {id: '', isSelected: false}
}