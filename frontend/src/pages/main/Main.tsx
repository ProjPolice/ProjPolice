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
        <div style={{ margin: '2%', alignSelf : 'start' }}>최근프로젝트</div>
      <ProjectList />
        <div style={{ margin: '2%', alignSelf : 'start' }}>임박한 프로젝트</div>
      <TaskList />
    </Page>
  );
}

export default Main;
