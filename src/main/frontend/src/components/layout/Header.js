import React from 'react'
import '../../assets/css/Header.css';
import Logo from '../../assets/images/벼랑위의포뇨.png';

const Header = () => {

  return (
    <header>
      <img src={ Logo } className='logo-image' alt="Logo" />
    </header>
  );
};

export default Header;