export interface ProductResponseBody {
    productId: string;
    name: string;
    price: number;
    quantity: number;
    img: string;
}

export interface OrderRequestBody{
    productsId: string[];
    email: string;
    method: number;
}