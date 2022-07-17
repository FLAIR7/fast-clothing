import styled from 'styled-components';
import React from 'react'
import {Product} from "./Product";
import storeItems from "../../item.json";

const Container = styled.div`
    padding: 20px;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
`;

export function Products() {

  return (
    <Container>
      {storeItems.map(item => (
          <Product key={item.id} {...item}/>
      ))}
      </Container>
  )
}