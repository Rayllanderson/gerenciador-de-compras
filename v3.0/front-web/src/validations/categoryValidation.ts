import {CategoryPostBody, CategoryPutBody} from "../interfaces/categoryInterface";
import {isNotEmpty} from "./inputValidation";

export function validateSave(category: CategoryPostBody){
    return new Promise<string>(function (resolve, reject) {
        const nameIsEmpty = !isNotEmpty(category.name);
        if (nameIsEmpty) reject({message: 'Nome não pode ser vazio.'});
        resolve('The fields has been successful validated.')
    })
}

export function validateEdit(category: CategoryPutBody){
    const promiseId = new Promise<string>(function (resolve, reject) {
        if (category.id == null) reject({reason: 'Id está nulo', message: 'Recarregue a página e tente novamente'});
        else resolve ('The id is not null. All good :)')
    })
    const promiseRestOfData = validateSave({name: category.name, budget: category.budget});
    return Promise.all([promiseId, promiseRestOfData]);
}