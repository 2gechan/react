import { Outlet } from "react-router-dom";
import "./css/App.css";
import MainNav from "./comps/MainNav";
import "./css/MainNav.css";
import "./css/join.css";

function App() {
  return (
    <div className="App">
      <header>
        <MainNav />
      </header>
      <Outlet />
    </div>
  );
}

export default App;