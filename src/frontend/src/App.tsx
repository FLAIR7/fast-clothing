import React from 'react';
import './App.css';
import { Navbar} from './components/navbar/Navbar';
import { Routes, Route} from "react-router-dom";
import Home from './pages/home/Home';
import Login from './pages/login/Login';
import { Register} from './pages/register/Register';
import { AuthProvider } from './contexts/AuthContext';
import { RegisterProvider } from './contexts/RegisterContext';
import { ToastProvider } from './contexts/ToastContext';
import { LoginProvider } from './contexts/LoginContext';
import { PaginationProvider } from './contexts/PaginationContext';
import { ShoppingCartProvider } from './contexts/ShoppingCartContext';

function App() {

  return (
      <>
      <ToastProvider>
          <AuthProvider>
            <LoginProvider>
              <RegisterProvider>
                <PaginationProvider>
                <ShoppingCartProvider>
                  <Navbar/>
                  <Routes>
                    <Route path='/' element={<Home/>}></Route>
                    <Route path='/login' element={<Login/>}></Route>
                    <Route path='/register' element={<Register/>}></Route>
                  </Routes>
                  </ShoppingCartProvider>
                </PaginationProvider>
              </RegisterProvider>
            </LoginProvider>
          </AuthProvider>
        </ToastProvider>

         
      </>
  );
}

export default App;
