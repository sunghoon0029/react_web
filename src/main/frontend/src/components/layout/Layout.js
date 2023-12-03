import React from "react";
import NavBar from "./NavBar";
import Header from "./Header";
import Footer from "./Footer";

const Layout = ({ children }) => {

    return (
        <div>
            <NavBar />

            <Header />

            <main>
                {children}
            </main>

            <Footer />
        </div>
    );
};

export default Layout;