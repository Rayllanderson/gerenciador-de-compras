import {CategoryPostBody} from "../interfaces/categoryInterface";
import {isNotEmpty} from "./inputValidation";

export function validateSave(category: CategoryPostBody){
    return new Promise<string>(function (resolve, reject) {
        const nameIsEmpty = !isNotEmpty(category.name);
        if (nameIsEmpty) reject({message: 'Nome não pode ser vazio.'});
        resolve('The fields has been successful validated.')
    })
}