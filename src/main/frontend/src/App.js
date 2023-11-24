import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Join from './pages/member/Join';
import ReduxJoin from './pages/member/ReduxJoin';
import Login from './pages/member/Login';
import BoardList from './pages/board/BoardList';

const App = () => {

  return (
    <div>
      <Router>
        <Routes>
          <Route path='/' element={ <Home /> } />
          <Route path='/join' element={ <Join /> } />
          <Route path='/jointest' element={ <ReduxJoin /> } />
          <Route path='/login' element={ <Login /> } />
          <Route path='/board' element={ <BoardList /> } />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
