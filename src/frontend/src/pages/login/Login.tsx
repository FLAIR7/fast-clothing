import TextField  from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import React, {FC, ReactElement, useEffect, useState} from "react";
import { Constainer, FormContainer, Headline, Text } from "./Login-styles";
import { makeStyles } from "@material-ui/core";
import { useMediaQuery } from "react-responsive";
import { classicNameResolver } from "typescript";

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


const Login: FC = (loginUser): ReactElement => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const onClickSignIn = (userData: { email: "", password: ""}): void => {

    };

    const classes = useStyles();

    return(
        <Constainer>
            <FormContainer className={classes.root}>
                <Headline>Already have an account?</Headline>
                <Text>Login with your email and password</Text>
                <TextField
                    id="email"
                    type="email"
                    label="E-mail Address"
                    variant="outlined"
                />
                <TextField 
                    id="password"
                    type="password"
                    label="Password"
                    variant="outlined"
                />
                <Button variant="contained" color="primary">
                    Login
                </Button>
            </FormContainer>
        </Constainer>
    );
}

export default Login;