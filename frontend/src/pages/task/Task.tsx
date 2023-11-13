import { Page } from '@assets/design/globalStyles';
import Board from './components/Board';
import { TextContainer } from './TaskStyle';
function Task() {
  return (
    <Page>
      <TextContainer>
        <h3>내 작업 관리</h3>
      </TextContainer>
      <Board />
    </Page>
  );
}

export default Task;
