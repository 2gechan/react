import React from "react";
import ReactDOM from "react-dom/client";
import "./css/index.css";
import MainRouter from "./router/MainRouter";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <MainRouter />
  </React.StrictMode>
);
