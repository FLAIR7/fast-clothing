import { useContext, useEffect } from "react";
import { Offcanvas, Stack, Button } from "react-bootstrap";
import { PaginationContext } from "../../contexts/PaginationContext";
import { useShoppingCart } from "../../contexts/ShoppingCartContext";
import ProductController from "../../controllers/productController";
import { CartItem } from "../cart-item/CartItem";
import { formatCurrency } from "../../utils/formatCurrency";
import api from "../../services/api";
import {OrderContext} from "../../contexts/OrderContext";
import { AuthContext } from "../../contexts/AuthContext";
import { useNavigate } from "react-router-dom";
import { ToastContext} from "../../contexts/ToastContext";

type ShoppingCartProps = {
    isOpen: boolean
}

export function ShoppingCart({isOpen}: ShoppingCartProps){
    const {closeCart, cartItems} = useShoppingCart();
    const {pagination, loadPage} = useContext(PaginationContext);
    const {save, setThings} = useContext(OrderContext);
    const {user} = useContext(AuthContext);
    const controller = new ProductController();

    const history = useNavigate();
    const {addToast} = useContext(ToastContext);

    function orders(){

        if(user === null || user === undefined) {
            history('/login');
            closeCart();
        } else {
            const token = JSON.parse(localStorage.getItem("@FastCloth:auth_token") || "");
            const order = {
                "productsId":
                    cartItems.map(item => item.id),
                "email": Object.values(user)[0],
                "method": 1
            }
            api.post("/orders", order, {headers: {"Authorization": `Bearer ${token}`}})
                .then(() => {
                    addToast({
                        type: 'success',
                        title: 'Order successful',
                        description: 'Your products will arrive in 1 minute'
                    }); 
                    alert('Your products will arrive in 1 minute');                
                }).catch(err => {
                    console.log(err);
                })
        }
    }

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
                <Button onClick={() => orders()}>SHOP NOW!</Button>
            </Offcanvas.Body>
        </Offcanvas>
    )
}
