import React, { createContext, ReactNode, useCallback, useContext, useState } from 'react'
import api from '../services/api';
import { OrderRequestBody } from '../types/productTypes';
import { ToastContext } from './ToastContext';
import {useNavigate} from "react-router-dom";
import { AuthContext } from './AuthContext';

interface OrderProviderProps {
    children: ReactNode;
}

interface OrderContextData {
    productsId: string[],
    email: string,
    method: number,
    save: (body: OrderRequestBody) => Promise<void>,
    setThings: () => void,
}

export function useOrder(){
    return useContext(OrderContext);
}

export const OrderContext = createContext({} as OrderContextData)

export function OrderProvider({children}: OrderProviderProps){

    const [productsId, setProductsId] = useState<string[]>([]);
    const [email, setEmail] = useState<string>('');
    const [method, setMethod] = useState<number>(1);


    const {user} = useContext(AuthContext);
    const {addToast} = useContext(ToastContext);
    const history = useNavigate();

    const save = useCallback(async () => {
        const orderToBeSaved: OrderRequestBody = {
            productsId: productsId,
            email: email,
            method: method
        }
        const token = JSON.parse(localStorage.getItem("@FastCloth:auth_token") || "");
        await api.post('/orders', orderToBeSaved, {headers: {"Authorization": `Bearer ${token}`}})
    }, [productsId, email, method, api])

    function setThings() {
        
        
    }

    return (
        <OrderContext.Provider value={{
            productsId, 
            email, 
            method, 
            save, 
            setThings}}>
            {children}
        </OrderContext.Provider>
    );
}