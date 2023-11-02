import { BoardContainer, Task, BoardSection } from '../TaskStyle';


function Board( ) {
  return (
    <BoardContainer height={'80%'} background="white">
      <Task backgroundColor='#2391EE'>
        <BoardSection backgroundColor='white'/>
        <BoardSection backgroundColor='white'/>
        <BoardSection backgroundColor='white'/>
      </Task>
    </BoardContainer>
  );
}

export default Board;
