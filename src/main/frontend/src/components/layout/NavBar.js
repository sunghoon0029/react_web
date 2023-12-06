import React from 'react'
import { Container, Nav, Navbar } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { logoutUser } from '../../modules/user';

const NavBar = () => {

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const isLoggedIn = useSelector(state => state.user.isLoggedIn);

  const logout = () => {
    dispatch(logoutUser());

    alert('로그아웃 성공');
    navigate('/');
  };

  return (
    <Navbar collapseOnSelect expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="/">Web</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/board">게시판</Nav.Link>
            {!isLoggedIn ? (
              <>
                <Nav.Link href="/login">로그인</Nav.Link>   
                <Nav.Link href="/join">회원가입</Nav.Link>    
              </>
            ) : (
              <>
                <Nav.Link href='/mypage'>마이페이지</Nav.Link>
                <Nav.Link onClick={ logout }>로그아웃</Nav.Link>
              </>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavBar;