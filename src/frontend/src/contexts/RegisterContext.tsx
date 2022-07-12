import React, {createContext, ReactNode, useState, useContext, useCallback} from "react";
import {useNavigate} from "react-router-dom";
import { UserPostRequest } from "../types/types";
import { AuthContext } from "./AuthContext";
import {validateRegister} from "../validations/userValidation";

interface RegisterContextProviderProps {
    children: ReactNode,
}

interface RegisterContextData {
    email: string,
    password: string,
    register: () => void,
    handleEmailChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handlePasswordChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
}

export const RegisterContext = createContext<RegisterContextData>({} as RegisterContextData);

export function RegisterProvider({children}: RegisterContextProviderProps) {
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');

    const {signUp} = useContext(AuthContext);

    const history = useNavigate();

    const register = useCallback(async () => {
        const user: UserPostRequest = {
            email: email,
            password: password,
        }
        await validateRegister(user).then(async () => {
            await signUp({email, password}).then(() => {
                history('/');
                alert('success inside signUp');
            }).catch(err => {
                alert('error inside signUp function');
            }); 
        }).catch(err => {
            alert('error inside validateRegister');
        });
    }, [email, password, signUp, history]);

    const handleEmailChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value)
    }, []);

    const handlePasswordChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value)
    }, []);

    return (
        <RegisterContext.Provider value={{
            email, password,
            register,
            handlePasswordChange, handleEmailChange,
        }}>
            {children}
        </RegisterContext.Provider>
    );
}