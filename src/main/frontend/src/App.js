import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import ReduxJoin from './pages/member/ReduxJoin';
import BoardList from './pages/board/BoardList';
import ReduxLogin from './pages/member/ReduxLogin';
import Test from './pages/Test';

const App = () => {

  return (
    <div>
      <Router>
        <Routes>
          <Route path='/' element={ <Home /> } />
          <Route path='/join' element={ <ReduxJoin /> } />
          <Route path='/login' element={ <ReduxLogin /> } />
          <Route path='/board' element={ <BoardList /> } />
          <Route path='/test' element={ <Test /> } />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
