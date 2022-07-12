import React, {useContext} from "react"; 
import { Container, FormContainer, Headline, Image, Text } from "./Register-styles";
import { useMediaQuery } from "react-responsive";
import { Button, TextField } from "@material-ui/core";
import { RegisterContext } from "../../contexts/RegisterContext";
import { LoadingContext } from "../../contexts/LoadingContext";
import { makeStyles } from "@material-ui/core";


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

export function Register() {
    const {
        register, handleEmailChange, handlePasswordChange,
        email, password
    } = React.useContext(RegisterContext);

    const classes = useStyles();

    const {btnIsLoading} = useContext(LoadingContext);

    const isMobile = useMediaQuery({ query: "(max-width: 1024px)"});
    
    return (
        <Container>
            {isMobile ? "" : <Image/>}
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

// const user = {
//     email: "diego@gmail.com",
//     password: "diego123"
// }

// function saveUser(){
//     api.post('/login', user)
//     .then(res => {
//         console.log(res.data)
//     })
//     .catch(error => {
//         console.log(error);
//     })
// }

// function pegaTokenJWTePrintaNoConsoleDaPagina(){
//     const params = new URLSearchParams();
//     params.append('username', 'milgrau@gmail.com');
//     params.append('password', 'milgrau123');
//     api.post('/login', params)
//     .then(res => {
//         console.log(res);
//     })
//     .catch(error => {
//         console.log(error);
//     });
// }
