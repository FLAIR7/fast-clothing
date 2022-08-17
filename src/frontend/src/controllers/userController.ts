import api from "../services/api";
import { getAuthHeader } from "../services/auth";
import { UserPostRequest } from "../types/types";

type order = {
    productsId: string[],
    userId: string,
    method: number
}

const create = (data: UserPostRequest) => {
    return api.post<UserPostRequest>('/users', data);
}

export const makeOrder = (data: order) => {
    return api.post<order>('/orders', data, {headers: {Authorization: 'Bearer ' + getAuthHeader()}});
}