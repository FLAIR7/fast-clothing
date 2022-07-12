import React from 'react';
import './App.css';
import Navbar from './components/Navbar/Navbar';
import { BrowserRouter, Routes, Route} from "react-router-dom";
import Home from './components/Home/Home';
import Login from './pages/login/Login';
import {Register} from './pages/register/Register';
import api from "./services/api";
import { AuthProvider } from './contexts/AuthContext';
import { RegisterProvider } from './contexts/RegisterContext';

function App() {

  return (
      <>
        <AuthProvider>
          <RegisterProvider>
            <Navbar/>
            <Routes>
              <Route path='/' element={<Home/>}></Route>
              <Route path='/login' element={<Login/>}></Route>
              <Route path='/register' element={<Register/>}></Route>
            </Routes>
          </RegisterProvider>
        </AuthProvider>

         
      </>
  );
}

export default App;
