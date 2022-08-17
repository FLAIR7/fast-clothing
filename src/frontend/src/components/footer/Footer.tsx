import { Facebook, Instagram, LinkedIn, MailOutline, Phone, Room } from "@material-ui/icons";
import {
    Container,
    Left,
    Center,
    Title,
    Right,
    Logo,
    Desc,
    SocialContainer,
    SocialIcon,
    ContactItem
} from "./Footer-styles";

export const Footer = () => {
    return (
        <Container>
            <Left>
                <Logo>Fast Clothing.</Logo>
                <Desc>
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. 
                </Desc>
                <SocialContainer>
                    <SocialIcon color="3B5999">
                        <Facebook/>
                    </SocialIcon>
                    <SocialIcon color="E4405F">
                        <Instagram/>
                    </SocialIcon>
                    <SocialIcon color="0077b5">
                        <LinkedIn/>
                    </SocialIcon>
                </SocialContainer>
            </Left>
            <Center>
                <Title>About us</Title>
                <p>We are the best ecommerce in the world</p>
            </Center>
            <Right>
                <Title>Contact</Title>
                <ContactItem>
                    <Room style={{marginRight:"10px"}}/> Street Nowhere
                </ContactItem>
                <ContactItem>
                    <Phone style={{marginRight:"10px"}}/> +1 111 111
                </ContactItem>
                <ContactItem>
                    <MailOutline style={{marginRight:"10px"}}/> contact@fastclothing.com
                </ContactItem>
            </Right>
        </Container>
    );
}