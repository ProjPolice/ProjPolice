import { colors } from '@assets/design/colors';
import { css } from '@emotion/react';
import { ProjPoliceButtonProps } from '@interfaces/widgets';

function ProjPoliceButton({ width, height, color = colors.primary, context, onClick }: ProjPoliceButtonProps) {
  return (
    <div
      onClick={onClick}
      css={css`
        width: ${width}px;
        height: ${height}px;
        color: ${color};
        display: flex;
        align-items: center;
        justify-content: center;
        border-width: ${width / 25};
        border-style: solid;
        border-color: ${color};
        border-radius: 5px;
        padding: 0.3%;
        &:hover {
          opacity: 0.5;
          cursor: pointer;
        }
      `}
    >
      <p>{context}</p>
    </div>
  );
}

export default ProjPoliceButton;
