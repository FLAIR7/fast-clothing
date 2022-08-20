import React, {createContext, ReactNode, useContext, useState, useCallback} from 'react'
import { useNavigate } from 'react-router-dom';
import { UserPostRequest } from '../types/types';
import { validateLogin } from '../validations/userValidation';
import { AuthContext } from './AuthContext';
import { ToastContext } from './ToastContext';

interface LoginContextProviderProps {
    children: ReactNode;
}

interface LoginContextContextData {
    email: string,
    password: string,
    login: () => void,
    handleEmailChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
    handlePasswordChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
}

export const LoginContext = createContext<LoginContextContextData>({} as LoginContextContextData);

export function LoginProvider({children}: LoginContextProviderProps) {

    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');

    const {signIn} = useContext(AuthContext);
    const {addToast} = useContext(ToastContext);

    const history = useNavigate();

    const login = useCallback(async () => {
        const user: UserPostRequest = {
            email: email,
            password: password
        }
        await validateLogin(user).then(async () => {
            await signIn({email, password}).then(() => {
                addToast({
                    type: 'success',
                    title: 'Welcome!',
                    description: 'Sign in successfuly'
                });
                history("/");
            }).catch(err => {
                addToast({
                    type: 'error',
                    title: 'Error',
                    description: 'something went wrong!',
                });
            })
        }).catch(err => {
            addToast({
                type: 'error',
                title: 'error',
                description: 'something went wrong!',
            });
        });
    }, [email, password, signIn, addToast])

    const handleEmailChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value);
    }, [])

    const handlePasswordChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }, [])

    return (
        <LoginContext.Provider value={{
            email, password,
            login, 
            handleEmailChange, handlePasswordChange
        }}>
            {children}
        </LoginContext.Provider>
    );

}