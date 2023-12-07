import React from "react";
import { Container } from "react-bootstrap";
import NavBar from "./NavBar";
import Header from "./Header";
import Footer from "./Footer";

const Layout = ({ children }) => {

    return (
        <div>
            <NavBar />
            <Header />
            <main>{children}</main>
            <Footer />
        </div>
    );
};

export default Layout;