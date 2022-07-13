import styled from "styled-components";

export const Container = styled.div`
    display: flex;
    height: calc(100vh - 64px);

    @media (max-width: 1024px){
        align-items: center;
        justify-content: center;
        text-align: center;
    }

`;

export const Image = styled.div`
	background: url("https://images.unsplash.com/photo-1531185038189-41815d864f32?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=725&q=80")
		center center/cover;
	background-color: rgba(0, 0, 0, 0.5);
	background-blend-mode: color;
	min-width: 60%;
	-webkit-box-shadow: 11px 2px 48px -32px rgba(0, 0, 0, 0.75);
	-moz-box-shadow: 11px 2px 48px -32px rgba(0, 0, 0, 0.75);
	box-shadow: 11px 2px 48px -32px rgba(0, 0, 0, 0.75);
`;

export const FormContainer = styled.div`
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
