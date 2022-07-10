import Home from "./pages/Home";
import LoginPage from "./pages/Login";
import Navbar from "./components/Navbar";
import { BrowserRouter } from "react-router-dom";

const App = () => {
  return (
    <div>
      <BrowserRouter>
        <Home/>
      </BrowserRouter>
    </div>
  )
};


export default App;