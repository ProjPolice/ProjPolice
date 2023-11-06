import { Page } from '@assets/design/globalStyles';

import Header from './components/Header';
import ProjectList from './components/ProjectList';
import TaskList from './components/TaskList';

function Main() {
  return (
    <Page>
      <Header />
      <ProjectList />
      <TaskList />
    </Page>
  );
}

export default Main;
