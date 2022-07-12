import {createContext, ReactNode, useCallback, useState} from "react";
import api from "../services/api";
import {UserPostRequest} from "../types//types";

interface AuthContextData {
    user: object;
    signIn: (body: UserPostRequest) => Promise<void>
    signUp: (body: UserPostRequest) => Promise<void>
    signOut: () => void;
}

interface AuthProviderProps {
    children: ReactNode;
}

interface AuthState {
    token: string;
    user: object;
}

export const AuthContext = createContext<AuthContextData>({} as AuthContextData);

export function AuthProvider({children}: AuthProviderProps) {
    const [data, setData] = useState<AuthState>(() => {
        const token = localStorage.getItem("auth_token");
        const user = localStorage.getItem("user");
        if(token && user && user != 'undefined') {
            return {token, user: JSON.parse(user)};
        }
        return {} as AuthState;
    });

    const signIn = useCallback(async ({email, password}: UserPostRequest) => {
        const response = await api.post('login', {
            email, password
        });
        const {token, email2} = response.data;
        const user = {email, email2};
        localStorage.setItem('auth_token', token);
        localStorage.setItem('user', JSON.stringify(user));
        setData({token, user});
    }, [])

    const signUp = useCallback(async ({email, password}: UserPostRequest) => {
        await api.post('users', {
            email, password
        });
    }, []);

    const signOut = useCallback(() => {
        localStorage.removeItem('auth_token');
        localStorage.removeItem('user');
        setData({} as AuthState)
    }, [])

    return <AuthContext.Provider value={{user: data.user, signIn, signOut, signUp}}>
        {children}
    </AuthContext.Provider>

}