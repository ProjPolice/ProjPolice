import { Page } from './MainStyle';

import Header from './components/Header';
import ProjectList from './components/ProjectList';
import TaskList from './components/TaskList';
import NavigationBar from './components/Navigation/NavigationBar';

function Main() {
  return (
    <Page>
      <NavigationBar/>
      <Header />
      <ProjectList />
      <TaskList />
    </Page>
  );
}

export default Main;
