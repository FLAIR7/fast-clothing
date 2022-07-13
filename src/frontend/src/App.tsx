import React from 'react';
import './App.css';
import Navbar from './components/Navbar/Navbar';
import { BrowserRouter, Routes, Route} from "react-router-dom";
import Home from './pages/home/Home';
import Login from './pages/login/Login';
import {Register} from './pages/register/Register';
import api from "./services/api";
import { AuthProvider } from './contexts/AuthContext';
import { RegisterProvider } from './contexts/RegisterContext';
import { ToastProvider } from './contexts/ToastContext';
import { LoginProvider } from './contexts/LoginContext';
import { PaginationProvider } from './contexts/PaginationContext';

function App() {

  return (
      <>
      <ToastProvider>
          <AuthProvider>
            <LoginProvider>
              <RegisterProvider>
                <PaginationProvider>
                  <Navbar/>
                  <Routes>
                    <Route path='/' element={<Home/>}></Route>
                    <Route path='/login' element={<Login/>}></Route>
                    <Route path='/register' element={<Register/>}></Route>
                  </Routes>
                </PaginationProvider>
              </RegisterProvider>
            </LoginProvider>
          </AuthProvider>
        </ToastProvider>

         
      </>
  );
}

export default App;
