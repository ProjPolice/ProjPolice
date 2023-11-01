import { HeaderBoxProps } from '@interfaces/main';
import { Box } from '@main/MainStyle';


function HeaderBox({ count, name, icon, backgroundColor }: HeaderBoxProps) {
  return (
    <Box backgroundColor={backgroundColor}>
      <div style={{ flex: 1 }}>
        <img src={icon} alt="Icon" />
      </div>
      <div style={{ flex: 1 }}>
        <p>{count} 건</p>
        <p>{name}</p>
      </div>
    </Box>
  );
}

export default HeaderBox;
