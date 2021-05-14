import api from "../services/api";
import {getAuthHeader} from "../services/auth";
import {Pageable, PageType} from "../interfaces/page";
import {AxiosResponse} from "axios";
import {AllProductPostRequestBody, ProductPutBody} from "../interfaces/productInterface";
import {SelectItem, TransferAllProductRequestBody} from "../interfaces/selectItemInterface";

export default class AllProductController implements Pageable {
    BASE_URL: string;

    constructor() {
        this.BASE_URL = '/all-products';
    }

    post(data: AllProductPostRequestBody) {
        return api.post(this.BASE_URL, data, {headers: getAuthHeader()});
    }

    put(data: ProductPutBody) {
        return api.put(`${this.BASE_URL}/${data.id}`, data, {headers: getAuthHeader()});
    }

    delete(id: string) {
        return api.delete(this.BASE_URL + '/' + id, {headers: getAuthHeader()});
    }

    deleteVarious(data:SelectItem[]){
        return api.delete(this.BASE_URL, {headers: getAuthHeader(), data: data});
    }

    copyProductsToAnotherCategory(data: TransferAllProductRequestBody){
        return api.post(`${this.BASE_URL}/copy`, data, {headers: getAuthHeader()});
    }

    moveProductsToAnotherCategory(data: TransferAllProductRequestBody){
        return api.post(`${this.BASE_URL}/move`, data, {headers: getAuthHeader()});
    }

    fetchStatistics() {
        return api.get(`${this.BASE_URL}/statistics`, {headers: getAuthHeader()})
    }

    findByName(search: string, page: number, size?: number): Promise<AxiosResponse> {
        return api.get(`${this.BASE_URL}/search?name=${search}&page=${page}&size=${size}`, {headers: getAuthHeader()});
    }

    getPageable(page: number, sort: string, order: string, pageType: PageType = {type: 'all'}, size?: number): Promise<AxiosResponse> {
        const url = pageType.type === 'all' ? this.BASE_URL : `${this.BASE_URL}/${pageType.type}`;
        return api.get(`${url}?size=${size}&page=${page}&sort=${sort},${order}`, {headers: getAuthHeader()});
    }
}