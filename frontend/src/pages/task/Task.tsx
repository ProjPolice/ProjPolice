import { Page } from '@assets/design/globalStyles';
import Board from './components/Board';
import { TextContainer } from './TaskStyle';
// import CreateTaskModal from '@widgets/modals/CreateTaskModal';
// import CreateProjectkModal from '@widgets/modals/CreateProjectModal';
function Task() {
  return (
    <Page>
      {/* <CreateTaskModal /> */}
      {/* <CreateProjectkModal /> */}
      <TextContainer>
        <h3>내 작업 관리</h3>
      </TextContainer>
      <Board />
    </Page>
  );
}

export default Task;
