import React, { createContext, ReactNode, useContext, useState } from 'react'
import api from '../services/api';
import { OrderRequestBody } from '../types/productTypes';
import { ToastContext } from './ToastContext';
import {useNavigate} from "react-router-dom";
import { AuthContext } from './AuthContext';

interface OrderProviderProps {
    children: ReactNode;
}

interface OrderContext {
    productsId: string[],
    email: string,
    method: number,
    saveOrder: (items: string[]) => void,
}

export function useOrder(){
    return useContext(OrderContext);
}

export const OrderContext = createContext({} as OrderContext)

export function OrderProvider({children}: OrderProviderProps){

    const [productsId] = useState<string[]>([]);
    const [email] = useState<string>('');
    const [method] = useState<number>(1);

    const {user} = useContext(AuthContext);
    const {addToast} = useContext(ToastContext);

    const history = useNavigate();

    function saveOrder(items: string[]) {
        if(user === null || user === undefined) {
            history("/login");
            addToast({
                type: 'info',
                title: "Must Login",
                description: "You must login to buy!"
            })
            alert('You must login to buy!');
            return;
        }
        const orderToBeSaved: OrderRequestBody = {
            productsId: items,
            email: Object.values(user)[0],
            method: 1
        }
        const token = JSON.parse(localStorage.getItem("@FastCloth:auth_token") || "");
        api.post('/orders', orderToBeSaved, {headers: {"Authorization": `Bearer ${token}`}})
            .then(() => {
                addToast({
                    type: 'success',
                    title: "Order Successful",
                    description: "Your Products will arrive in 1 minute!"
                });
                alert('Your Products will arrive in 1 minute!');
            }).catch(err => {
                addToast({
                    type: "error",
                    title: "Error in order",
                    description: "Something went wrong while make your order"
                });
            })   
    }

    return (
        <OrderContext.Provider value={{
            productsId, 
            email, 
            method, 
            saveOrder,
            }}>
            {children}
        </OrderContext.Provider>
    );
}