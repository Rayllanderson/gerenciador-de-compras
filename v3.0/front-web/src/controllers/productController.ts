import api from "../services/api";
import {getAuthHeader} from "../services/auth";
import {Pagination} from "../interfaces/paginationData";
import {AxiosResponse} from "axios";
import {ProductPostBody, ProductPutBody} from "../interfaces/productInterface";

export default class ProductController implements Pagination {
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

    put(id: string, data: ProductPutBody) {
        return api.put(this.BASE_URL + '/' + id, data, {headers: getAuthHeader()});
    }

    delete(id: string) {
        return api.delete(this.BASE_URL + '/' + id, {headers: getAuthHeader()});
    }

    findByName(query: string) {
        return api.get(this.BASE_URL + '/search?name=' + query, {headers: getAuthHeader()});
    }

    findAllPageable(size: number, page: number) {
        return api.get(`${this.BASE_URL}?size=${size}&page=${page}`, {headers: getAuthHeader()});
    }

    getAllPageable(page: number, size:number = 20): Promise<AxiosResponse> {
        return api.get(`/products?size=${size}&page=${page}`, {headers: getAuthHeader()});
    };
}