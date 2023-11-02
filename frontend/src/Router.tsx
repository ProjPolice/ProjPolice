import { createBrowserRouter } from 'react-router-dom';

import App from './App';

import Main from './pages/main/Main';
import Project from './pages/project/Project';

import Profile from './pages/profile/Profile';
import Task from './pages/task/Task';

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
    ],
  },
]);

export default router;
