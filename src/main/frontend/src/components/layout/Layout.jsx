import React from "react";
import NavBarElements from "../NavBar/NavBarElements";
import Footer from "./footer/Footer";

const Layout = ({ children }) => {

    return (
        <div>
            <NavBarElements />

            <main>
                {children}
            </main>

            <Footer />
        </div>
    );
};

export default Layout;