import React from 'react'
import '../../assets/css/Layout.css'
import { Link } from 'react-router-dom';
import Logo from '../../assets/images/gray.png';

const Header = () => {

  return (
    <header>
        <div className="logo-container">
            <Link to={"/"}>
              <div className="logo-wrapper">
                <img src={ Logo } className="logo-image" alt="Logo" />
                <span className="logo-text">Main Logo</span>
              </div>
            </Link>
        </div>
    </header>
  );
};

export default Header;