import { useContext, useEffect } from "react";
import { Offcanvas, Stack, Button } from "react-bootstrap";
import { PaginationContext } from "../../contexts/PaginationContext";
import { useShoppingCart } from "../../contexts/ShoppingCartContext";
import ProductController from "../../controllers/productController";
import { CartItem } from "../cart-item/CartItem";
import { formatCurrency } from "../../utils/formatCurrency";
import { useOrder } from "../../contexts/OrderContext";

interface ShoppingCartProps {
    isOpen: boolean
}

export function ShoppingCart({isOpen}: ShoppingCartProps){
    const {closeCart, cartItems} = useShoppingCart();
    const {pagination, loadPage} = useContext(PaginationContext);
    const {saveOrder} = useOrder();
    const controller = new ProductController();

    const cartIds = cartItems.map(function(o, i) {
        return o.id
    })

    useEffect(() => {
        loadPage(controller);
    }, [loadPage])

    return(
        <Offcanvas show={isOpen} onHide={closeCart} placement="end">
            <Offcanvas.Header closeButton>
                <Offcanvas.Title>Cart</Offcanvas.Title>
            </Offcanvas.Header>
            <Offcanvas.Body>
                <Stack gap={3}>
                    {cartItems.map(item => (
                        <CartItem key={item.id} {...item}/>
                    ))}
                    <div className="ms-auto fw-bold fs-5">
                        Total{" "}
                        {formatCurrency(
                            cartItems.reduce((total, cartItem) => {
                                const item = pagination.content.find(i => i.productId === cartItem.id)
                                return total + (item?.price || 0) * cartItem.quantity;
                            }, 0)
                        )}
                    </div>
                </Stack>
                <Button onClick={() => saveOrder(cartIds)}>SHOP NOW!</Button>
            </Offcanvas.Body>
        </Offcanvas>
    );
}
