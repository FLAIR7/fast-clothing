
import { AxiosResponse } from "axios"
import { ProductResponseBody } from "./productTypes"

export interface Page {
    content: ProductResponseBody[],
    first: boolean,
    last: boolean,
    empty: boolean,
    totalPages: number,
    size: number,
    number: number,
    totalElements: number,
    numberOfElements: number,
}

export interface Pageable {
    getPageable(page: number, sort: string, order:string, size?: number): Promise<AxiosResponse>,
}