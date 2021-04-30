import {isNotEmpty} from "./inputValidation";
import {ProductPostBody} from "../interfaces/productInterface";

export function validateSave(product: ProductPostBody){
    return new Promise<string>(function (resolve, reject) {
        const nameIsEmpty = !isNotEmpty(product.name);
        if (nameIsEmpty) reject({message: 'Nome não pode ser vazio.'});
        resolve('The fields has been successful validated.')
    })
}