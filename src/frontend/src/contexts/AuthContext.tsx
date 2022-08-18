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
        const token = localStorage.getItem('@FastCloth:auth_token');
        const user = localStorage.getItem('@FastCloth:user');
        if(token && user && user !== 'undefined') {
            return {token, user: JSON.parse(user)};
        }
        return {} as AuthState;
    });

    const signIn = useCallback(async ({email, password}: UserPostRequest) => {
        const params = new URLSearchParams();
        params.append('username', email);
        params.append('password', password);
        const response = await api.post('login', params);
        const {email2} = response.data;
        const token = JSON.stringify(response.data);
        const user = {email, email2};
        window.localStorage.setItem('@FastCloth:auth_token', token);
        window.localStorage.setItem('@FastCloth:user', JSON.stringify(user));
        setData({token, user});
    }, [])

    const signUp = useCallback(async ({email, password}: UserPostRequest) => {
        await api.post('users', {
            email, password
        });
    }, []);

    const signOut = useCallback(() => {
        localStorage.removeItem('@FastCloth:auth_token');
        localStorage.removeItem('@FastCloth:user');
        setData({} as AuthState)
    }, [])

    return <AuthContext.Provider value={{user: data.user, signIn, signOut, signUp}}>
        {children}
    </AuthContext.Provider>

}