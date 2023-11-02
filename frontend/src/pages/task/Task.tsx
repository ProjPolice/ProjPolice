import { Page } from './TaskStyle';
import NavigationBar from '../../common/widgets/NavigationBar';
import Board from './Board/Board';


function Task() {
  return (
    <Page>
      <NavigationBar/>
        <div style={{ margin: '1% 0% 1% 10%', alignSelf : 'start' }}>내 작업 관리</div>
      <Board/>
    </Page>
  );
}

export default Task;
