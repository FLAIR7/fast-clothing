import styled from "styled-components";

export const Container = styled.div`
    display: flex;
    background-color: black;
    color: white;
    overflow: hidden;
    @media only screen and (max-width: 1024px) {
        flex-direction: column;
        text-align: justify;
        align-items: center;
    }
`;

export const Left = styled.div`
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
`;

export const Logo = styled.h1`
`;

export const Desc = styled.p`
    margin: 20px; 0px;
`;

export const SocialContainer = styled.div`
    display: flex;
`;

export const SocialIcon = styled.div`
    width: 40px;
    height: 40px;
    border-radius: 50%;
    color: white;
    background-color: #${props=>props.color};
    display: flex;
    align-items: center;
    justify-content: center;
    margin-right: 20px;
    `;

export const Center = styled.div`
    flex: 1;
    padding: 20px;
    @media only screen and (max-width: 1024px) {
        display: none;
    }
`;

export const Title = styled.h3`
    margin-bottom: 30px;
`;

export const List = styled.ul`

`;

export const ListItem = styled.li`
    
`;

export const Right = styled.div`
    flex: 1;
    padding: 20px;
`;

export const ContactItem = styled.div`
    margin-bottom: 20px;
    display: flex;
    align-items: center;
`;
