import { PaginationContext } from "../../contexts/PaginationContext";
import { Pageable } from "../../types/pageTypes";
import { useContext, useEffect} from "react";
import { ProductResponseBody } from "../../types/productTypes";
import { StoreItem } from "./StoreItem";
import styled from "styled-components";
import LoadingSpinner from "../spinner/LoadingSpinner";


interface Props {
    controller: Pageable;
}

const Container = styled.div`
    padding: 20px;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
`;

const Text = styled.div`
    font-size: 40px;
    height: 50vh;
    text-align: center;
`;

export default function ProductList({controller}: Props){
    const {pagination, loadPage} = useContext(PaginationContext);

    useEffect(() => {
        loadPage(controller);
    }, [loadPage])

    return (
        <Container>
            
          {pagination.content.length === 0 ? 
            <Text>
                <h1>None product found</h1>
                <p>The Back-end is probably asleep</p>
                <LoadingSpinner/>
            </Text>
          : pagination.content.map((product: ProductResponseBody | {}) => 
                 product && <StoreItem product={product as ProductResponseBody} key={JSON.stringify(product)}/>
          )} 
             
        </Container>
    )
}
