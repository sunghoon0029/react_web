import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Join from './pages/member/Join';
import Login from './pages/member/Login';
import BoardList from './pages/board/BoardList';
import BoardWrite from './pages/board/BoardWrite';

const App = () => {

  return (
    <div>
      <Router>
        <Routes>
          <Route path='/' element={ <Home /> } />

          <Route path='/join' element={ <Join /> } />
          <Route path='/login' element={ <Login /> } />

          <Route path='/board' element={ <BoardList /> } />
          <Route path='/board/write' element={ <BoardWrite /> } />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
