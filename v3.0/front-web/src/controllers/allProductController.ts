import api from "../services/api";
import {getAuthHeader} from "../services/auth";
import {Pageable} from "../interfaces/page";
import {AxiosResponse} from "axios";

export default class AllProductController implements Pageable {
    BASE_URL: string;

    constructor() {
        this.BASE_URL = '/all-products';
    }

    fetchStatistics() {
        return api.get(`${this.BASE_URL}/statistics`, {headers: getAuthHeader()})
    }

    findByName(search: string, page: number, size?: number): Promise<AxiosResponse> {
        return {} as Promise<AxiosResponse>
    }

    getAllPageable(page: number, sort: string, order: string, size?: number): Promise<AxiosResponse> {
        return {} as Promise<AxiosResponse>
    }
}