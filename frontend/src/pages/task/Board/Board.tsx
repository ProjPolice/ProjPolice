import { BoardContainer, Task, BoardSection} from '../TaskStyle';
import BoardItem from './BoardItem';


function Board( ) {
  return (
    <BoardContainer height={'80%'} background="white">
      <Task backgroundColor='#2391EE'>
        <BoardSection backgroundColor='#FFF2F2'>
          <BoardItem name={'프로젝트명'} backgroundColor="#FF9292"></BoardItem>
        </BoardSection>
        <BoardSection backgroundColor='#FFFDEE'>
        <BoardItem name={'프로젝트명'} backgroundColor="#FFEB80"></BoardItem>
          </BoardSection>
        <BoardSection backgroundColor='#F0FFEF'>
          <BoardItem name={'프로젝트명'} backgroundColor="#24FF00"></BoardItem>
        </BoardSection>
      </Task>
    </BoardContainer>
  );
}

export default Board;
