import api from "../services/api";
import { AxiosResponse } from "axios";

export default class ProductController {

    findAll(){
        return api.get('/products');
    }

    getPageable(page: number, sort: string, order:string = 'asc', size:number =20):Promise<AxiosResponse>{
        return api.get(`/products?size=${size}&page=${page}&sort=${sort},${order}`)
    }
}