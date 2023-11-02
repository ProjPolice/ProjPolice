import { Page } from './MainStyle';

import Header from './components/Header';
import ProjectList from './components/ProjectList/ProjectList';
import TaskList from './components/TaskList';
import NavigationBar from '../../common/widgets/NavigationBar';

function Main() {
  return (
    <Page>
      <NavigationBar/>
      <Header />
        <div style={{ margin: '2% 0% 2% 10%', alignSelf : 'start' }}>최근프로젝트</div>
      <ProjectList />
        <div style={{ margin: '2% 0% 2% 10%', alignSelf : 'start' }}>임박한 프로젝트</div>
      <TaskList />
    </Page>
  );
}

export default Main;

