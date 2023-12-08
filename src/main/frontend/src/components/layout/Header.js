import React from 'react'
import '../../assets/css/Header.css';
import Logo from '../../assets/images/벼랑위의포뇨.png';
import { Link } from 'react-router-dom';

const Header = () => {

  return (
    <header>
      <Link to="/">
        <img src={ Logo } className='logo-image' alt="Logo" />
      </Link>
    </header>
  );
};

export default Header;