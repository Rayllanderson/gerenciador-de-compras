import {CategoryResponseBody} from "./categoryInterface";
import {AxiosResponse} from "axios";

export interface PaginationData {
    content: CategoryResponseBody[],
    first: boolean,
    last: boolean,
    empty: boolean,
    totalPages: number,
    size: number,
    number: number,
    totalElements: number,
    numberOfElements: number,
}

export interface Pagination {
    getAllPageable(page: number, size?: number): Promise<AxiosResponse>,
}