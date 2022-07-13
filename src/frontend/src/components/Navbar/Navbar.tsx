import { Button } from "@material-ui/core";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { CartButton, Items, Logo, StyledAppBar, StyledToolbar } from "./Navbar.styles";

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

                        <Button color="inherit" onClick={() => history('/login')}>
                            Login
                        </Button>
                        <Button color="inherit" onClick={() => history('/register')}>
                            Register
                        </Button>
                    
                    <CartButton>
                    </CartButton>
                </Items>
            </StyledToolbar>
        </StyledAppBar>
    );
}

export default Navbar;