import api from "../services/api";
import {getAuthHeader} from "../services/auth";
import {CategoryPostBody, CategoryPutBody} from "../interfaces/categoryInterface";
import {Pagination} from "../interfaces/paginationData";
import {AxiosResponse} from "axios";

export default class CategoryController implements Pagination {
    findAll() {
        return api.get('/categories', {headers: getAuthHeader()});
    }

    post(data: CategoryPostBody) {
        return api.post('/categories', data, {headers: getAuthHeader()});
    }

    findById(id: string) {
        return api.get('/categories/' + id, {headers: getAuthHeader()});
    }

    put(id: string, data: CategoryPutBody) {
        return api.put('/categories/' + id, data, {headers: getAuthHeader()});
    }

    delete(id: string) {
        return api.delete('/categories/' + id, {headers: getAuthHeader()});
    }

    findByName(query: string) {
        return api.get('/categories/search?q=' + query, {headers: getAuthHeader()});
    }

    findAllPageable(size: number, page: number) {
        return api.get(`/categories?size=${size}&page=${page}`, {headers: getAuthHeader()});
    }

    getAllPageable(page: number, size:number = 20): Promise<AxiosResponse> {
        return api.get(`/categories?size=${size}&page=${page}`, {headers: getAuthHeader()});
    };

    //TODO: delete vários e mover categoria
}