import api from "./api";
import { UserPostRequest } from "../types/types";

const create = (data: UserPostRequest) => {
    return api.post<UserPostRequest>('/users', data);
}