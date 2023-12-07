import React from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import { logoutUser } from '../../modules/user';
import { Container, Nav, Navbar } from 'react-bootstrap';

const NavBar = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const isLoggedIn = useSelector(state => state.user.isLoggedIn);

  const logout = () => {
    dispatch(logoutUser());
    navigate('/');
  };

  return (
    <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand as={Link} to="/">Web</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/board">게시판</Nav.Link>
          </Nav>
          <Nav className="ml-auto">
            {!isLoggedIn ? (
              <>
                <Nav.Link as={Link} to="/login">로그인</Nav.Link>
                <Nav.Link as={Link} to="/join">회원가입</Nav.Link>              
              </>
            ) : (
              <>
                <Nav.Link as={Link} to="/mypage">마이페이지</Nav.Link>
                <Nav.Link onClick={logout}>로그아웃</Nav.Link>              
              </>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavBar;