import { HeaderBoxProps } from '@interfaces/main';
import { LeftLinedBox } from '@main/MainStyle';

function HeaderItem({ count, name, icon, backgroundColor }: HeaderBoxProps) {
  return (
    <LeftLinedBox backgroundColor={backgroundColor}>
      <div style={{ flex: 1 }}>
        <img src={icon} alt="Icon" />
      </div>
      <div style={{ flex: 1 }}>
        <p>{count} 건</p>
        <p>{name}</p>
      </div>
    </LeftLinedBox>
  );
}

export default HeaderItem;
