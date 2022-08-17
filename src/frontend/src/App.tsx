import React from 'react';
import './App.css';
import { Navbar} from './components/navbar/Navbar';
import { AuthProvider } from './contexts/AuthContext';
import { RegisterProvider } from './contexts/RegisterContext';
import { ToastProvider } from './contexts/ToastContext';
import { LoginProvider } from './contexts/LoginContext';
import { PaginationProvider } from './contexts/PaginationContext';
import { ShoppingCartProvider } from './contexts/ShoppingCartContext';
import { OrderProvider } from './contexts/OrderContext';
import { Pages } from './routes/Pages';

function App() {

  return (
      <>
      <ToastProvider>
          <AuthProvider>
            <LoginProvider>
              <RegisterProvider>
                <PaginationProvider>
                <OrderProvider>
                <ShoppingCartProvider>
                    <Navbar/>
                    <Pages/>
                  </ShoppingCartProvider>
                  </OrderProvider>
                </PaginationProvider>
              </RegisterProvider>
            </LoginProvider>
          </AuthProvider>
        </ToastProvider>
      </>
  );
}

export default App;
