import { createContext, ReactNode, useCallback, useContext, useState } from "react";
import { Page, Pageable} from "../types/pageTypes"
import { createAnEmptyPagination } from "../utils/createAnEmptyPaginatio";
import { ToastContext } from "./ToastContext";
import data from "../item.json";


interface PaginationProviderProps {
    children: ReactNode;
}

interface PaginationContextData {
    pagination: Page,
    loadPage: (controller: Pageable) => void,
    setPagination: (page: Page) => void,
    size: number,
    setSize: (size: number) => void,
    handleSortChange: (e: any) => void,
    handleSizeChange: (e: any) => void,
    handleOrderChange: (e: any) => void,
    sort: string,
    order: 'asc' | 'desc',
    setSort: (sortBy: string) => void,
    setOrder: (value: 'asc' | 'desc') => void,
}

export const PaginationContext = createContext<PaginationContextData>({} as PaginationContextData);

export function PaginationProvider({children}: PaginationProviderProps) {

    const [pagination, setPagination] = useState<Page>(createAnEmptyPagination());
    const [size, setSize] = useState<number>(10);
    const [sort, setSort] = useState<string>('');
    const [order, setOrder] = useState<'asc' | 'desc'>('asc');
    
    const {addToast} = useContext(ToastContext);

    let i = 0;

    for(const elements of pagination.content) {
        elements.img = data[i].img;
        i++;
    }

    const loadPage = useCallback(async (controller: Pageable) => {
        await controller.getPageable(0, sort, order, size).then((response) => {
            setPagination(response.data);
        }).catch(err => addToast({
            type: 'error',
            title: 'Error',
            description: 'error in paginationcontext'
        }))
    }, [size, sort, order, addToast, ])

    const handleSortChange = useCallback((e: any) => {
        setSort(e.target.value)
    }, [])

    const handleSizeChange = useCallback((e: any) => {
        setSize(e.target.value)
    }, [])

    const handleOrderChange = useCallback((e: any) => {
        setOrder(e.target.value)
    }, [])

    return (
        <PaginationContext.Provider value={{
            pagination, setPagination, loadPage, size, setSize,
            handleOrderChange, order, setOrder,
            handleSortChange, sort, setSort,
            handleSizeChange, 
        }}>
            {children}
        </PaginationContext.Provider>
    )
}