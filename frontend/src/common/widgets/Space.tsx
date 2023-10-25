import { css } from '@emotion/react';

interface SpaceProps {
  direction: 'row' | 'col';
  size: number;
}

function Space({ direction = 'row', size = 1 }: SpaceProps) {
  return (
    <div
      css={css({
        width: direction === 'row' ? size : 0,
        height: direction === 'col' ? size : 0,
      })}
    ></div>
  );
}

export default Space;
