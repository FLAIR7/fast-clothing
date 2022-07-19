import { useContext, useEffect } from "react";
import { Offcanvas, Stack, Button } from "react-bootstrap";
import { PaginationContext } from "../../contexts/PaginationContext";
import { useShoppingCart } from "../../contexts/ShoppingCartContext";
import ProductController from "../../controllers/productController";
import { CartItem } from "../cart-item/CartItem";
import { formatCurrency } from "../../utils/formatCurrency";
import api from "../../services/api";
import { getAuthHeader } from "../../services/auth";

type ShoppingCartProps = {
    isOpen: boolean
}

export function ShoppingCart({isOpen}: ShoppingCartProps){
    const {closeCart, cartItems} = useShoppingCart();
    const {pagination, loadPage} = useContext(PaginationContext);
    const controller = new ProductController();

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
                <Button onClick={() => order()}>SHOP NOW!</Button>
            </Offcanvas.Body>
        </Offcanvas>
    )
}
const ordem = {
    "productsId": [
        "4d955512-5277-472c-84b3-f40fc420f371"
    ],
    "email": "diego@gmail.com",
    "method": 1
}

function order(){
    const token = JSON.parse(localStorage.getItem("@FastCloth:auth_token") || "");
    console.log(token);
    api.post("/orders", ordem, {headers: {"Authorization": `Bearer ${token}`}}).then(res => {
        console.log(res.data)
    }).catch(err => {
        console.log(err);
    })
}