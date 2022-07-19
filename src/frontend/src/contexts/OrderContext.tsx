import React, { createContext, ReactNode, useCallback, useContext, useState } from 'react'
import { OrderRequestBody } from '../types/productTypes';
import { ToastContext } from './ToastContext';

type OrderProviderProps = {
    children: ReactNode;
}

interface OrderContextData{
    productsId: string[],
    email: string,
    method: number
}

export function useOrder(){
    return useContext(OrderContext);
}

const OrderContext = createContext<OrderContextData>({} as OrderContextData)

export function OrderProvider({children}: OrderProviderProps){

    const [productsId, setProductsId] = useState<string[]>([]);
    const [email, setEmail] = useState<string>('');
    const [method, setMethod] = useState<number>(1);

    const {addToast} = useContext(ToastContext);

    const save = useCallback(async () => {
        const orderToBeSaved: OrderRequestBody = {
            productsId: productsId,
            email: email,
            method: method
        }
    }, [])

    return (
        <OrderContext.Provider value={{productsId, email, method}}>
            {children}
        </OrderContext.Provider>
    )
}