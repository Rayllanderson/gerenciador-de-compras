import {PaginationData} from "../interfaces/paginationData";

export function createAnEmptyPagination(): PaginationData {
    return {
        content: [],
        empty: true,
        first: true,
        last: true,
        numberOfElements: 0,
        number: 0,
        size: 0,
        totalElements: 0,
        totalPages: 0
    }
}