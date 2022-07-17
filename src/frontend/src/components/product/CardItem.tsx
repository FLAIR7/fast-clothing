import { Button } from "@material-ui/core";
import { FavoriteBorderOutlined, ShoppingCartOutlined } from "@material-ui/icons";
import styled from "styled-components";
import { ProductResponseBody } from "../../types/productTypes";

interface Props {
    product: ProductResponseBody
}

const Container = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
`;

const Title = styled.div`
    font-weight: 500;
    letter-spacing: 1px;
`; 

export function CardItem({product}: Props){
    return (
    <Container>
        <div>
            <Title>{product.name}</Title>
            <Button variant="contained" color="primary">SHOP NOW!</Button>
        </div>
    
    </Container>      
    )
}