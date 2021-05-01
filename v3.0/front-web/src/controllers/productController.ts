import api from "../services/api";
import {getAuthHeader} from "../services/auth";
import {Pageable} from "../interfaces/page";
import {AxiosResponse} from "axios";
import {ProductPostBody, ProductPutBody} from "../interfaces/productInterface";
import {TransferProduct} from "../interfaces/trasnferProductInterface";

export default class ProductController implements Pageable {
    BASE_URL: string;

    constructor(categoryId: string) {
        this.BASE_URL = '/categories/' + categoryId + '/products';
    }

    findAll() {
        return api.get(this.BASE_URL, {headers: getAuthHeader()});
    }

    post(data: ProductPostBody) {
        return api.post(this.BASE_URL, data, {headers: getAuthHeader()});
    }

    findById(id: string) {
        return api.get(this.BASE_URL + '/' + id, {headers: getAuthHeader()});
    }

    put(data: ProductPutBody) {
        return api.put(`${this.BASE_URL}/${data.id}`, data, {headers: getAuthHeader()});
    }

    delete(id: string) {
        return api.delete(this.BASE_URL + '/' + id, {headers: getAuthHeader()});
    }

    copyProductsToAnotherCategory(data: TransferProduct){
        return api.post(`${this.BASE_URL}/copy`, data, {headers: getAuthHeader()});
    }

    findByName(search:string, page:number, size?:number) {
        return api.get(`${this.BASE_URL}/search?name=${search}&page=${page}&size=${size}`, {headers: getAuthHeader()});
    }

    findAllPageable(size: number, page: number) {
        return api.get(`${this.BASE_URL}?size=${size}&page=${page}`, {headers: getAuthHeader()});
    }

    getAllPageable(page: number, size:number = 20): Promise<AxiosResponse> {
        return api.get(`${this.BASE_URL}?size=${size}&page=${page}`, {headers: getAuthHeader()});
    };
}