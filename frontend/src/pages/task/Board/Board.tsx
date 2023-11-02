import { BoardContainer, Task, BoardSection } from '../TaskStyle';


function Board( ) {
  return (
    <BoardContainer height={'80%'} background="white">
      <Task backgroundColor='#2391EE'>
        <BoardSection backgroundColor='#FFF2F2' flex="1">aaaa</BoardSection>
        
        <BoardSection backgroundColor='#FFFDEE' flex="1">aaaaa</BoardSection>
        
        <BoardSection backgroundColor='#F0FFEF' flex="1">aaaaa</BoardSection>
      </Task>
    </BoardContainer>
  );
}

export default Board;
