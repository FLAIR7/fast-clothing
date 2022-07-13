import { ProductResponseBody } from "../../types/productTypes";

interface Props {
    product: ProductResponseBody
}

export function CardItem({product}: Props) {
    return (
        <div>
            <p>{product.productId}</p>
            <p>{product.name}</p>
            <p>{product.price}</p>
        </div>
    )
}