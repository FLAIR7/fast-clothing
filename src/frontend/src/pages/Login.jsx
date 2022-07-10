import React, { useState } from "react";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import styled from "styled-components";
import { makeStyles } from "@material-ui/core";
import { useMediaQuery } from "react-responsive";

const useStyles = makeStyles((theme) => ({
	root: {
		"& .MuiTextField-root": {
			margin: theme.spacing(1),
			width: "100%",
		},
		"& .MuiButton-root": {
			margin: theme.spacing(1),
		},
	},
}));


const Container = styled.div`
    display: flex;
    width: 100vw;
    height: calc(100vh - 64px);

    @media (max-width: 1024px) {
        align-items: center;
        justify-content: center;
        text-align: center;
    }
`;

const FormContainer = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-width: 40%;
    padding: 0 50px;
`;

const Headline = styled.h2`
    font-weight: 500;
    margin: 0;
    font-size: 1.8rem;
`;

const Text = styled.h4`
    font-weight: 400;
    font-size: 1.1rem;
    margin: 5px 0px 10px 0;
`;

const Image = styled.div`
background: url("https://i.ibb.co/cXFnLLV/3.png")
center center/cover;
background-color: rgba(0, 0, 0, 0.5);
background-blend-mode: color;
min-width: 60%;
-webkit-box-shadow: 11px 2px 48px -32px rgba(0, 0, 0, 0.75);
-moz-box-shadow: 11px 2px 48px -32px rgba(0, 0, 0, 0.75);
box-shadow: 11px 2px 48px -32px rgba(0, 0, 0, 0.75);

`;

const LoginPage = ({ loginUser}) => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const isMobile = useMediaQuery({ query: "(max-width: 1024px)"})

    const handleSubmit = async () => {
        try {
            await loginUser({
                variables: { email, password },
            });
        } catch({ message }) {
            alert(message);
        }
    }

    const classes = useStyles();

    return(
        <Container>
            <FormContainer className={classes.root}>
                <Headline>Already have an account?</Headline>
                <Text>Login with your email and password</Text>
                <TextField
                    id="email"
                    type="email"
                    label="E-mail Address"
                    variant="outlined"
                    value={email}
                    onChange={({target: { value } }) => setEmail(email)}
                />
                <TextField
                    id="password"
                    type="password"
                    label="Password"
                    variant="outlined"
                    value={password}
                    onChange={({ target: { value } }) => setPassword(password)}
                />
                <Button variant="contained" color="primary" onClick={handleSubmit}>
                    Login
                </Button>
            </FormContainer>
            {isMobile ? "" : <Image/>}
        </Container>
    );
}

export default LoginPage;