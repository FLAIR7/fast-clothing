import React, { createContext, ReactNode, useContext, useState } from 'react'
import api from '../services/api';
import { OrderRequestBody } from '../types/productTypes';
import { ToastContext } from './ToastContext';
import {useNavigate} from "react-router-dom";
import { AuthContext } from './AuthContext';
import Swal from 'sweetalert2';

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

    const {user, signOut} = useContext(AuthContext);
    const {addToast} = useContext(ToastContext);

    const history = useNavigate();

    function saveOrder(items: string[]) {
        if(user === null || user === undefined) {
            history("/login");
            Swal.fire({
                heightAuto: false,
                icon: 'error',
                title: 'Must Login',
                text: "You must login to buy!"
            })
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
                Swal.fire({
                    heightAuto: false,
                    icon: 'success',
                    title: 'Order made!',
                    text: "Your Products will arrive in 1 minute!"
                })
            }).catch(err => {
                Swal.fire({
                    heightAuto: false,
                    icon: 'error',
                    title: 'Error in order',
                    text: "Sign out..."
                })
                console.log(err);
                signOut();
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