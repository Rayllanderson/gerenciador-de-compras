import {isNotEmpty} from "./inputValidation";
import {ProductPostBody, ProductPutBody} from "../interfaces/productInterface";

export function validateSave(product: ProductPostBody){
    return new Promise<string>(function (resolve, reject) {
        const nameIsEmpty = !isNotEmpty(product.name);
        if (nameIsEmpty) reject({message: 'Nome não pode ser vazio.'});
        resolve('The fields has been successful validated.')
    })
}

export function validateEdit(product: ProductPutBody){
    const promiseId = new Promise<string>(function (resolve, reject) {
        if (product.id == null) reject({reason: 'Id está nulo', message: 'Recarregue a página e tente novamente'});
        else resolve ('The id is not null. All good :)')
    })
    const promiseRestOfData = validateSave({name: product.name,
        stipulatedPrice: product.stipulatedPrice, spentPrice: product.spentPrice, purchased: product.purchased});
    return Promise.all([promiseId, promiseRestOfData]);
}