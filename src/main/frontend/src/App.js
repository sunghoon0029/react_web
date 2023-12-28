import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';

import Join from './pages/member/Join';
import Login from './pages/member/Login';
import MyPage from './pages/member/MyPage';
import Profile from './pages/member/Profile';
import ProfileEdit from './pages/member/ProfileEdit';

import BoardList from './pages/board/BoardList';
import BoardWrite from './pages/board/BoardWrite';
import BoardDetail from './pages/board/BoardDetail';
import BoardUpdate from './pages/board/BoardUpdate';
import MultipleFileUpload from './components/file/MultipleFileUpload';
import FileUpload from './components/file/FileUpload';

const App = () => {

  return (
    <div>
      <Router>
        <Routes>
          <Route path='/' element={ <Home /> } />

          <Route path='/join' element={ <Join /> } />
          <Route path='/login' element={ <Login /> } />
          <Route path='/mypage' element={ <MyPage /> } />
          <Route path='/mypage/profile' element={ <Profile /> } />
          <Route path='/mypage/profile-edit' element={ <ProfileEdit /> } />

          <Route path='/board' element={ <BoardList /> } />
          <Route path='/board/write' element={ <BoardWrite /> } />
          <Route path='/file' element={ <FileUpload /> } />
          <Route path='/files' element={ <MultipleFileUpload /> } />
          <Route path='/board/:id' element={ <BoardDetail /> } />
          <Route path='/board/update/:id' element={ <BoardUpdate /> } />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
