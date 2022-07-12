import { Button } from "@material-ui/core";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { CartButton, Items, Logo, StyledAppBar, StyledToolbar } from "./Navbar.styles";

const Container = styled.div`
    height: 60px;
    background-color: black;
`;

const Navbar = () => {

    const history = useNavigate();

    return (
        <StyledAppBar>
            <StyledToolbar>
                <Logo onClick={() => history('/')}>Fast Clothing</Logo>
                <Items>
                    <Button color="inherit" onClick={() => history('/shop')}>
                        Shop
                    </Button>
                    <>
                        <Button color="inherit">
                            Login
                        </Button>
                        <Button color="inherit">
                            Register
                        </Button>
                    </>
                    <CartButton>
                    </CartButton>
                </Items>

            </StyledToolbar>
        </StyledAppBar>
    );
}

export default Navbar;