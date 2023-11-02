import { BoardItemProps } from '@interfaces/main';

import { BoardBox } from '../TaskStyle';


function BoardItem({ name, backgroundColor }: BoardItemProps) {
  return (
    <BoardBox backgroundColor={backgroundColor}>
      <div style={{ flex: 1 }}>
        <p>{name}</p>
      </div>
    </BoardBox>
  );
}


export default BoardItem;


