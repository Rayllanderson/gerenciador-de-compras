import api from "../services/api";
import {getAuthHeader} from "../services/auth";
import {Pageable, PageType} from "../interfaces/page";
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

    getPageable(page: number, sort: string, order: string, pageType: PageType = {type: 'all'}, size?: number): Promise<AxiosResponse> {
        return {} as Promise<AxiosResponse>
    }
}