import styled from "styled-components";

export const Info = styled.div`
    opacity: 0;
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    background-color: rgba(0, 0, 0, 0.3);
    z-index: 3;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.5s ease;
    cursor: pointer;
`; 

export const Container = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    flex: 1;
    margin: 5px;
    min-width: 280px;
    height: 350px;
    background-color: #f5fbfd;
    position: relative;
    &:hover ${Info}{
    opacity: 1;
    }
`;

export const Title = styled.div`
  font-weight: 500;
  letter-spacing: 1px;
`; 

export const Image = styled.img`
    height: 75%
`;

export const Icon = styled.div`
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background-color: white;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 10px;
    transition: all 0.3s ease;

    &:hover {
    background-color: #e9f5f5;
    transform: scale(1.1);
    }
`;

export const Text = styled.p`
    color: white;
    font-weight: bold;
    font-size: 16px;
`;