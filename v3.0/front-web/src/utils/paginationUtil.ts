import {Page} from "../interfaces/page";

export function createAnEmptyPagination(): Page {
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