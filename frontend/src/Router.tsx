import { createBrowserRouter } from 'react-router-dom';

import App from './App';

import Main from './pages/main/Main';
import Project from './pages/project/Project';

import Profile from './pages/profile/Profile';
import Task from './pages/task/Task';
import { Test } from '../Test';

import Login from './pages/user/Login';
import SignUp from 'pages/user/SignUp';

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: '',
        element: <Main />,
      },
      {
        path: 'project/:project_id',
        element: <Project />,
      },
      {
        path: 'profile',
        element: <Profile />,
      },
      {
        path: 'task',
        element: <Task />,
      },
      {
        path: 'test',
        element: <Test />,
      },
      {
        path: 'login',
        element: <Login />,

      },
      {
        path: 'signup',
        element: <SignUp />,

      }
    ],
  },
]);

export default router;
