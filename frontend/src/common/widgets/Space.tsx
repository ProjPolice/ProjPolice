import { css } from '@emotion/react';
import { useEffect, useState } from 'react';

interface SpaceProps {
  direction: 'row' | 'col';
  size: number;
}

function Space({ direction = 'row', size = 1 }: SpaceProps) {
  const [windowSize, setWindowSize] = useState({
    width: window.innerWidth,
    height: window.innerHeight,
  });

  useEffect(() => {
    function handleResize() {
      setWindowSize({
        width: window.innerWidth,
        height: window.innerHeight,
      });
    }

    window.addEventListener('resize', handleResize);

    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  return (
    <div
      css={css({
        width: direction === 'col' ? (windowSize.width * size) / 100 : 0,
        height: direction === 'row' ? (windowSize.height * size) / 100 : 0,
      })}
    ></div>
  );
}

export default Space;
