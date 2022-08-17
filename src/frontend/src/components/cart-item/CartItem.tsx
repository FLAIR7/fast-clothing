import { useShoppingCart } from "../../contexts/ShoppingCartContext"
import { useContext, useEffect} from "react";
import { PaginationContext } from "../../contexts/PaginationContext";
import ProductController from "../../controllers/productController";
import { Stack, Button} from "react-bootstrap";
import { formatCurrency } from "../../utils/formatCurrency";

type CartItemProps = {
    id: string,
    quantity: number
}

export function CartItem({id, quantity}: CartItemProps){
    const { 
        removeFromCart,
        increaseCartQuantity, 
        decreaseCartQuantity
    } = useShoppingCart();
    const {pagination, loadPage} = useContext(PaginationContext);
    const controller = new ProductController();

    useEffect(() => {
        loadPage(controller);
    }, [loadPage])


    const item = pagination.content.find(item => item.productId === id);
    if(item == null) return null

    return (
        <Stack direction="horizontal" gap={2} className="d-flex
        align-items-center">
            <img src={item.img} style={{width: "125px", 
            height: "90px", objectFit: "cover"}}/>
        
            <div className="me-auto">
                <div>
                    {item.name}{" "}
                    X{quantity}
                </div>
                <div className="text-muted" style={{fontSize: ".65rem"}}>
                    {formatCurrency(item.price)}
                </div>
                <Button size="sm" onClick={() => increaseCartQuantity(item.productId)}>+</Button>
                {quantity !== 1 ? (
                <Button size="sm" className="btn btn-danger" onClick={() => decreaseCartQuantity(item.productId)}>-</Button>
                ) : (
                <Button size="sm" className="btn btn-danger" onClick={() => removeFromCart(item.productId)}>Remove</Button>)}
            </div>
        </Stack>
        
    )
}