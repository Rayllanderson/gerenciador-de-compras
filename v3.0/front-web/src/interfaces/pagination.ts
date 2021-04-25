import {CategoryResponseBody} from "./categoryInterface";

export interface Pagination {
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