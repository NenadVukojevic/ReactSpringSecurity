
import './App.css';
import NotFound from './components/NotFound';
import { Routes, Route } from 'react-router-dom';
import ShoppingList from './components/ShoppingList/ShoppingList';
import Login from './components/Login/Login';
import Hello from './components/Admin/Hello';
import RequireAuth from './components/hooks/RequireAuth';
import { Logout } from './components/Login/Logout';
import UserManagement from './components/Admin/UserManagement';

function App() {

  return (
      <Routes>

        <Route path="/login" element={<Login />} />
        <Route path="/logout" element={<Logout />} />
        <Route element={<RequireAuth />}>
          <Route path="/userManagement" element={<UserManagement />} />
          <Route path="/shopping" element={<ShoppingList />} />
          <Route path="/hello" element={<Hello />} />
          <Route path="*" element={<NotFound />} />
        </Route>
      </Routes>
  );
}

export default App;
