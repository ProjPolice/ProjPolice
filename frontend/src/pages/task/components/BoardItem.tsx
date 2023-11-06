import { BoardItemProps } from '@interfaces/main';

import { BoardBox } from '../TaskStyle';

function BoardItem({ name, backgroundColor }: BoardItemProps) {
  return (
    <BoardBox backgroundColor={backgroundColor}>
      <p>{name}</p>
    </BoardBox>
  );
}

export default BoardItem;
