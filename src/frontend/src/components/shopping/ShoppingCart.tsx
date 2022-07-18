import { useContext, useEffect } from "react";
import { Offcanvas, Stack, Button } from "react-bootstrap";
import { PaginationContext } from "../../contexts/PaginationContext";
import { useShoppingCart } from "../../contexts/ShoppingCartContext";
import ProductController from "../../controllers/productController";
import { CartItem } from "../cart-item/CartItem";
import { formatCurrency } from "../../utils/formatCurrency";
import api from "../../services/api";

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
                <Button>SHOP NOW!</Button>
            </Offcanvas.Body>
        </Offcanvas>
    )
}

/* Authorization jwt to make
const ordem = {
    "productsId": [
        "c2c9287f-e237-41f1-b339-7128788586f0"
    ],
    "userId": "e2b68022-ac7a-4888-98ff-2f80964e9d3c",
    "method": 1
}

function order(){
    api.post("/orders", ordem, {headers: {Authorization: 'Bearer' + localStorage.getItem("@Fast_Cloth:auth_token")}}).then(res => {
        console.log(res.data)
    }).catch(err => {
        console.log(err);
    })
}*/