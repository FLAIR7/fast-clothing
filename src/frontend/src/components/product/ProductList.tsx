import { PaginationContext } from "../../contexts/PaginationContext";
import { Pageable } from "../../types/pageTypes";
import { useContext, useEffect} from "react";
import { ProductResponseBody } from "../../types/productTypes";
import { CardItem } from "./CardItem";

interface Props {
    controller: Pageable;
}

export default function ProductList({controller}: Props){
    const {pagination, loadPage} = useContext(PaginationContext);

    useEffect(() => {
        loadPage(controller);
    }, [loadPage])

    return (
        <div>
            {pagination.content.length === 0 ? 
            <h1>None product found</h1>
            : pagination.content.map((product: ProductResponseBody | {}) => 
                product && <CardItem product={product as ProductResponseBody} key={JSON.stringify(product)}/>
            )}    
        
        </div>
    )
}