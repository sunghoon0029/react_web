import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import About from './pages/About';
import Project from './pages/Project';
import BoardList from './pages/BoardList';
import Join from './pages/Join';
import Login from './pages/Login';

const App = () => {

  return (
    <div>
      <Router>
        <Routes>
          <Route path='/' element={ <Home /> } />
          <Route path='/about' element={ <About /> } />
          <Route path='/project' element={ <Project /> } />
          <Route path='/board' element={ <BoardList /> } />
          <Route path='/join' element={ <Join /> } />
          <Route path='/login' element={ <Login /> } />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
