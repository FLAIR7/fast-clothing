import { createContext, ReactNode, useCallback, useContext } from "react";
import { AuthContext} from "./AuthContext";
import { ToastContext} from "./ToastContext";

interface LogoutContextProviderProps {
    children: ReactNode;
}

interface LogoutContextContextData {
    logout: () => void,
}

export const LogoutContext = createContext<LogoutContextContextData>({} as LogoutContextContextData);

export function LogoutProvider({children}: LogoutContextProviderProps) {

    const {signOut} = useContext(AuthContext);
    const {addToast} = useContext(ToastContext);

    const logout = useCallback(async () => {
        await signOut();
        addToast({
            type: 'info',
            title: 'Logout',
            description: 'logout success, see you later!',
        });
    }, [addToast, signOut])

    return (
        <LogoutContext.Provider value={{
            logout
        }}>
            {children}
        </LogoutContext.Provider>
    )

}