import React, { useContext, useState } from 'react'
import { Link } from 'react-router-dom';
import { useShoppingCart } from '../../contexts/ShoppingCartContext';
import { AuthContext } from '../../contexts/AuthContext';
import "./navbar.css"
import {FaBars, FaTimes} from 'react-icons/fa'
import { AiFillShopping } from 'react-icons/ai'
import { Button } from 'react-bootstrap';

export function Navbar(){
  const {openCart, cartQuantity} = useShoppingCart();
  const {user, signOut} = useContext(AuthContext);
  let username = user ? Object.values(user)[0].split("@")[0] : null;
  const [click, setClick] = useState(false);
  
  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  return (
    <>
      <nav className="navbar">
        <div className="navbar-container">
          <Link to='/' className='navbar-logo'>
            Fast Clothing
          </Link>
          <div className="menu-icon" onClick={handleClick}>
            <i>
              {click ? <FaTimes/> : <FaBars/>}
            </i>
          </div>
          {user === null || user === undefined ? (
          <ul className={click ? 'nav-menu active' : 'nav-menu'}>
            <li className="nav-item">
              <Link to='/' className='nav-links' onClick={closeMobileMenu}>
                Home
              </Link>
            </li>
            <li className="nav-item">
              <Link to='/login' className='nav-links' onClick={closeMobileMenu}>
                Login
              </Link>
            </li>
            <li className="nav-item">
              <Link to='/register' className='nav-links' onClick={closeMobileMenu}>
                Sign In
              </Link>
            </li>
            <li className="nav-item nav-links">
              <Button onClick={openCart} 
              style={{width: "3rem", height: "3rem", position: "relative"}} 
              variant="outline-primary" className="rounded-circle">
                <AiFillShopping style={{width: "20px", height: "25px"}}/>
              <div className="rounded-circle bg-danger d-flex justify-content-center
                align-items-center" style={{
                color: "white", 
                width: "1.5rem", 
                height: "1.5rem",
                position: "absolute", 
                bottom: 0, 
                right: 0,
                transform: "translate(25%, 25%)"}}>
                  {cartQuantity}
                </div>
              </Button>
            </li>
          </ul>
          ) : (
            <ul className={click ? 'nav-menu active' : 'nav-menu'}>
              <li className="nav-item">
                <Link to='/' onClick={() => signOut()} className="nav-links">
                  Logout
                </Link>
              </li>
              <li className="nav-item">
                <h5 className="nav-links">Hi, {username}</h5>
              </li>
              <li className="nav-item nav-links">
              <Button onClick={openCart} 
              style={{width: "3rem", height: "3rem", position: "relative"}} 
              variant="outline-primary" className="rounded-circle">
                <AiFillShopping style={{width: "20px", height: "25px"}}/>
              <div className="rounded-circle bg-danger d-flex justify-content-center
                align-items-center" style={{
                color: "white", 
                width: "1.5rem", 
                height: "1.5rem",
                position: "absolute", 
                bottom: 0, 
                right: 0,
                transform: "translate(25%, 25%)"}}>
                  {cartQuantity}
                </div>
              </Button>
            </li>
            </ul>
          )}
        </div>
      </nav>
    </>
  );
}
