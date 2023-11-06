import { HeaderBoxProps } from '@interfaces/main';
import { HeaderBox } from '@main/MainStyle';

function HeaderItem({ count, name, icon, backgroundColor }: HeaderBoxProps) {
  return (
    <HeaderBox backgroundColor={backgroundColor}>
      <div style={{ flex: 1 }}>
        <img src={icon} alt="Icon" />
      </div>
      <div style={{ flex: 1 }}>
        <p>{count} 건</p>
        <p>{name}</p>
      </div>
    </HeaderBox>
  );
}

export default HeaderItem;
