import { HeaderBoxProps } from '@interfaces/main';
import { Box } from '@main/MainStyle';

function HeaderBox({ count, name }: HeaderBoxProps) {
  return (
    <Box>
      <p>{name}</p>
      <p>{count} 건</p>
    </Box>
  );
}

export default HeaderBox;
