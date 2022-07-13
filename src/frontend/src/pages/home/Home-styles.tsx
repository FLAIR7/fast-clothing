import styled from 'styled-components';

export const Item = styled.div`
    height: 230px;
`;

export const Container = styled.div`
    height: 100%;
    width: 90%;
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-gap: 7px;
    margin: 50px auto;

    & ${Item}:nth-child(3) {
        grid-column: 1 / span 2;
        height: 330px;
      }
`;