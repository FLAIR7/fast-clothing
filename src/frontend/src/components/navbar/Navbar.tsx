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
  )

  /*return (
      <nav className="navbar">
        <div className="container">
          <h3 className="logo">Fast Clothing</h3>
            <ul className="nav-links">

              {user == null || user == undefined ?  (

              <ul>
                <Link to='/'>Home</Link>
                <Link to='/'>Login</Link>
                <Link to='/'>Sign In</Link>
              </ul>
              ) : (
                <ul>
                    <Link to='/' onClick={() => signOut()}>Logout</Link>
                    <h4>Hi, {username}</h4>
                </ul>
              )}
          </ul>
          <button className="mobile-menu-icon">
            <FaBars/>
          </button>
        </div>
      </nav>
  );*/

  // return (
  //   <NavbarBs sticky="top" className="bg-white shadow-sm mb-3" expand="lg">
  //     <Container>
  //       <NavbarBs.Brand>Fast Clothing</NavbarBs.Brand>
  //       <NavbarBs.Toggle aria-controls="navbarScroll"/>
  //       <NavbarBs.Collapse id="navbarScroll">
  //           <Nav>
  //             {user === null || user === undefined ? (
  //             <Nav>
  //               <Nav.Link to="/" as={NavLink}>
  //                 <HomeOutlined/>
  //               </Nav.Link>
  //               <Nav.Link to="/login" as={NavLink}>
  //                 Login
  //               </Nav.Link>
  //               <Nav.Link to="/register" as={NavLink}>
  //                 Register
  //               </Nav.Link>  
  //             </Nav>
  //             ) : (
  //             <Nav>
  //               <Nav.Link onClick={() => signOut()}>
  //                 Logout
  //               </Nav.Link>
  //               <NavbarBs.Brand>Hi, {username}</NavbarBs.Brand>
  //             </Nav>
              
  //             )}
  //           </Nav>
          {/* </NavbarBs.Collapse> */}
          
          /*</NavbarBs.Collapse>
          <Button onClick={openCart} 
            style={{width: "3rem", height: "3rem", position: "relative"}} 
            variant="outline-primary" className="rounded-circle">
            <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 576 512"
                  fill="currentColor"
                >
                  <path d="M96 0C107.5 0 117.4 8.19 119.6 19.51L121.1 32H541.8C562.1 32 578.3 52.25 572.6 72.66L518.6 264.7C514.7 278.5 502.1 288 487.8 288H170.7L179.9 336H488C501.3 336 512 346.7 512 360C512 373.3 501.3 384 488 384H159.1C148.5 384 138.6 375.8 136.4 364.5L76.14 48H24C10.75 48 0 37.25 0 24C0 10.75 10.75 0 24 0H96zM128 464C128 437.5 149.5 416 176 416C202.5 416 224 437.5 224 464C224 490.5 202.5 512 176 512C149.5 512 128 490.5 128 464zM512 464C512 490.5 490.5 512 464 512C437.5 512 416 490.5 416 464C416 437.5 437.5 416 464 416C490.5 416 512 437.5 512 464z" />
                </svg>
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

                
      </Container>
    </NavbarBs>*/
}
