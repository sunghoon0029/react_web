import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import ReduxJoin from './pages/member/ReduxJoin';
import Login from './pages/member/Login';
import BoardList from './pages/board/BoardList';
import ReduxLogin from './pages/member/ReduxLogin';

const App = () => {

  return (
    <div>
      <Router>
        <Routes>
          <Route path='/' element={ <Home /> } />
          <Route path='/join' element={ <ReduxJoin /> } />
          <Route path='/logintest' element={ <ReduxLogin /> } />
          <Route path='/login' element={ <Login /> } />
          <Route path='/board' element={ <BoardList /> } />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
