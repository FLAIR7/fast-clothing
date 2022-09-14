import TextField  from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import React, {useContext} from "react";
import { Constainer, FormContainer, Headline, Text } from "./Login-styles";
import { makeStyles } from "@material-ui/core";
import { LoginContext } from "../../contexts/LoginContext";

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

export default function LoginPage() {
    
    const {
        login, 
        handleEmailChange, 
        handlePasswordChange,
        email, password
    } = useContext(LoginContext);

    const classes = useStyles();

    return(
        <Constainer>
            <FormContainer className={classes.root} onSubmit={login}>
                <Headline>Already have an account?</Headline>
                <Text>Login with your e-mail and password</Text>
                <TextField
                    id="email"
                    type="email"
                    label="E-mail Address"
                    variant="outlined"
                    onChange={handleEmailChange}
                    value={email}
                    onSubmit={login}
                />
                <TextField 
                    id="password"
                    type="password"
                    label="Password"
                    variant="outlined"
                    onChange={handlePasswordChange}
                    value={password}
                    onSubmit={login}
                />
                <Button type="button" onClick={login} variant="contained" color="primary">
                    Login
                </Button>
            </FormContainer>
        </Constainer>
    );
}
