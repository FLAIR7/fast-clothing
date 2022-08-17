import React from "react"; 
import { Container, FormContainer, Headline, Text } from "./Register-styles";
import { Button, TextField } from "@material-ui/core";
import { RegisterContext } from "../../contexts/RegisterContext";
import { makeStyles } from "@material-ui/core";


const useStyles = makeStyles((theme) => ({
	root: {
		"& .MuiTextField-root": {
			margin: theme.spacing(1),
            width: '100%'
		},
		"& .MuiButton-root": {
			margin: theme.spacing(1),
		},
	},
}));

export function Register() {
    const {
        register, handleEmailChange, handlePasswordChange,
        email, password
    } = React.useContext(RegisterContext);

    const classes = useStyles();

    return (
        <Container>
            <FormContainer className={classes.root}>
                <Headline>Do not have an account?</Headline>
                <Text>Register with your e-mail and password</Text>
                <TextField
                    id="email"
                    type="email"
                    label="E-mail Address"
                    variant="outlined"
                    onChange={handleEmailChange}
                    value={email}
                />
                <TextField
                    id="password"
                    type="password"
                    label="Password"
                    variant="outlined"
                    onChange={handlePasswordChange}
                    value={password}
                />
                <Button onClick={register} variant="contained" color="primary">
                    Register
                </Button>
            </FormContainer>
        </Container> 
    );
}