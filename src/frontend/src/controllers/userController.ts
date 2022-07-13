import api from "../services/api";
import { UserPostRequest } from "../types/types";

const create = (data: UserPostRequest) => {
    return api.post<UserPostRequest>('/users', data);
}