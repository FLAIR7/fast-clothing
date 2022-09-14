import styled from "styled-components";

export const Constainer = styled.div`
    display: flex;
    width: 100vw;
    align-items: center;
    min-height: 70vh;
    justify-content: center;

    @media (max-width: 1024px){
        align-items: center;
        justify-content: center;
        text-align: center;
    }
`;

export const FormContainer = styled.form`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-width: 40%;
    padding: 0 50px;
`;

export const Headline = styled.h2`
    font-weight: 500;
    margin: 0;
    font-size: 1.8rem;

`;

export const Text = styled.h4`
    font-weight: 400;
    font-size: 1.1rem;
    margin: 5px 0px 10px 0;
`;