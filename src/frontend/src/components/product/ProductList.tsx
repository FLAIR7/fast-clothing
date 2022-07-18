import { PaginationContext } from "../../contexts/PaginationContext";
import { Pageable } from "../../types/pageTypes";
import { useContext, useEffect} from "react";
import { ProductResponseBody } from "../../types/productTypes";
import { StoreItem } from "./StoreItem";
import styled from "styled-components";
import data from "../../item.json";

interface Props {
    controller: Pageable;
}

const Container = styled.div`
    padding: 20px;
    display: flex;
    flex-wrap: wrap;
`;

export default function ProductList({controller}: Props){
    const {pagination, loadPage} = useContext(PaginationContext);

    useEffect(() => {
        loadPage(controller);
    }, [loadPage])

    let i = 0;

    for(const elements of pagination.content) {
        elements.img = data[i].img;
        i++;
    }

    return (
        <Container>
            
          {pagination.content.length === 0 ? 
            <h1>None product found</h1>
          : pagination.content.map((product: ProductResponseBody | {}) => 
                 product && <StoreItem product={product as ProductResponseBody} key={JSON.stringify(product)}/>
          )} 
             
        </Container>
    )
}