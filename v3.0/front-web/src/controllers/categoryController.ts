import api from "../services/api";
import {getAuthHeader} from "../services/auth";
import {CategoryPostBody, CategoryPutBody, TransferCategoryRequestBody} from "../interfaces/categoryInterface";
import {Pageable, PageType} from "../interfaces/page";
import {AxiosResponse} from "axios";
import {SelectItem} from "../interfaces/selectItemInterface";

export default class CategoryController implements Pageable {
    findAll() {
        return api.get('/categories', {headers: getAuthHeader()});
    }

    findAllNonPageable() {
        return api.get('/categories/non-pageable', {headers: getAuthHeader()});
    }

    post(data: CategoryPostBody) {
        return api.post('/categories', data, {headers: getAuthHeader()});
    }

    findById(id: string) {
        return api.get('/categories/' + id, {headers: getAuthHeader()});
    }

    put(data: CategoryPutBody) {
        console.log(data)
        return api.put('/categories/' + data.id, data, {headers: getAuthHeader()});
    }

    delete(id: string) {
        return api.delete('/categories/' + id, {headers: getAuthHeader()});
    }

    duplicateCategory(data:TransferCategoryRequestBody){
        return api.post('/categories/copy', data,{headers: getAuthHeader()});
    }

    deleteVarious(data:SelectItem[]){
        return api.delete('/categories', {headers: getAuthHeader(), data: data});
    }

    findByName(search:string, page:number, size?:number) {
        return api.get(`/categories/search?name=${search}&page=${page}&size=${size}`, {headers: getAuthHeader()});
    }

    getPageable(page: number, sort: string, order: string, pageType: PageType = {type: 'all'}, size?: number): Promise<AxiosResponse> {
        return api.get(`/categories?size=${size}&page=${page}&sort=${sort},${order}`, {headers: getAuthHeader()});
    }
}