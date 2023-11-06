import { colors } from '@assets/design/colors';
import { BoardContainer, Task, BoardSection } from '../TaskStyle';
import BoardItem from './BoardItem';

function Board() {
  return (
    <BoardContainer height={'90%'} background={colors.white}>
      <Task backgroundColor={colors.primary}>
        <BoardSection backgroundColor={colors.board1}>
          <BoardItem name={'프로젝트명'} backgroundColor={colors.red}></BoardItem>
        </BoardSection>
        <BoardSection backgroundColor={colors.board2}>
          <BoardItem name={'프로젝트명'} backgroundColor={colors.yellow}></BoardItem>
        </BoardSection>
        <BoardSection backgroundColor={colors.board3}>
          <BoardItem name={'프로젝트명'} backgroundColor={colors.green}></BoardItem>
        </BoardSection>
      </Task>
    </BoardContainer>
  );
}

export default Board;
