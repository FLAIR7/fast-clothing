import { PaginationContext } from "../../contexts/PaginationContext";
import { Pageable } from "../../types/pageTypes";
import { useContext, useEffect} from "react";
import { ProductResponseBody } from "../../types/productTypes";
import { CardItem } from "./CardItem";
import styled from "styled-components";

interface Props {
    controller: Pageable;
}

const Container = styled.div`
    padding: 20px;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
`;

const Image = styled.img`
    background: 
`;

export default function ProductList({controller}: Props){
    const {pagination, loadPage} = useContext(PaginationContext);

    useEffect(() => {
        loadPage(controller);
    }, [loadPage])

    return (
        <Container>
        
        
            
          {pagination.content.length === 0 ? 
            <h1>None product found</h1>
          : pagination.content.map((product: ProductResponseBody | {}) => 
                 product && <CardItem product={product as ProductResponseBody} key={JSON.stringify(product)}/>
          )} 
             
            
            
                
        
        </Container>
        //  {pagination.content.length === 0 ? 
        //     <h1>None product found</h1>
        //     : pagination.content.map((product: ProductResponseBody | {}) => 
        //         product && <CardItem product={product as ProductResponseBody} key={JSON.stringify(product)}/>
        //     )} 

    )
}