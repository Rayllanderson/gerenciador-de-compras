import {CategoryResponseBody} from "./categoryInterface";
import {AxiosResponse} from "axios";
import {ProductResponseBody} from "./productInterface";

export interface Page {
    content: CategoryResponseBody[] | ProductResponseBody[],
    first: boolean,
    last: boolean,
    empty: boolean,
    totalPages: number,
    size: number,
    number: number,
    totalElements: number,
    numberOfElements: number,
}

export interface PageType {
    type: 'all' | 'purchased' | 'non-purchased'
}

export interface Pageable {
    getPageable(page: number, sort: string, order:string, type: PageType, size?: number): Promise<AxiosResponse>,
    findByName(search: string, page: number, size?: number): Promise<AxiosResponse>
}