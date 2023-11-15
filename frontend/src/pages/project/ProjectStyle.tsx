import { colors } from '@assets/design/colors';
import styled from '@emotion/styled';

export const CenterContainer = styled.div<{ background: string }>`
  display: flex;
  flex-direction: column;
  width: 70%;
  height: 100%;
  align-items: center;
  background-color: ${(props) => props.background};
`;

export const ProjectSection = styled.div<{ height: string; background: string }>`
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  width: 100%;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
`;

export const Container = styled.div<{ width: string; height: string }>`
  display: flex;
  flex-direction: column;
  align-content: center;
  overflow: hidden;
  margin: 0%;
  background-color: ${colors.white};
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  border-radius: 10px;
  border: 1px solid ${colors.default};
  box-sizing: border-box;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const SectionHeader = styled.div<{ height: string; background: string; alignItems: string }>`
  display: flex;
  background-color: ${(props) => props.background};
  width: 92%;
  height: ${(props) => props.height};
  align-items: ${(props) => props.alignItems};
`;

export const ContainerNav = styled.div<{ height: string; background: string }>`
  display: flex;
  justify-content: space-between;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
  width: 100% - 20px;
  padding: 0% 20px;
  align-items: center;
`;

export const ContainerContent = styled.div<{ width: string; height: string; background: string }>`
  display: flex;
  height: ${(props) => props.height};
  width: 100%;
  justify-content: center;
  align-items: center;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
  width: ${(props) => props.width};
`;

export const PositionContainer = styled.div<{ width: string; height: string }>`
  position: relative;
  display: flex;
  flex-direction: column;
  height: ${(props) => props.height};
  width: ${(props) => props.width};
  justify-content: end;
`;

export const DayContainer = styled.div<{ width: string; height: string; background: string }>`
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
  width: ${(props) => props.width};
`;

export const TimeLineContainer = styled.div<{ width: string; height: string; background: string }>`
  position: absolute;
  display: flex;
  flex-direction: column;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
  width: ${(props) => props.width};
  z-index: 1;
  overflow-y: auto;
  ::-webkit-scrollbar {
    display: none;
  }
`;

export const TimeLineBar = styled.div<{ width: number; height: string; background: string; empty: number }>`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 2%;
  background: linear-gradient(to right, transparent ${(props) => props.empty}%, ${(props) => props.background} 0%);
  width: ${(props) => props.width}%;
  height: ${(props) => props.height};
  min-height: 25%;
  z-index: 1;
  border-radius: 0px 5px 5px 0px;
`;

export const TimelineInfoLeft = styled.div`
  display: flex;
  width: 80px;
  justify-content: space-between;
  align-items: center;
  margin-left: 13px;
`;

export const CalendarDayBox = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  border-right: 1px solid ${colors.default};
`;

export const DayNav = styled.div<{ width: string; height: string; background: string; isToday: boolean }>`
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  background-color: ${(props) => (props.isToday ? colors.selected : props.background)};
  height: ${(props) => props.height};
  width: ${(props) => props.width};
  border-bottom: 2px solid ${colors.default};
`;

export const DetailContainer = styled.div<{ width: string; height: string; background: string; flexdirection: string }>`
  display: flex;
  flex-direction: ${(props) => props.flexdirection};
  /* justify-content: center; */
  /* align-items: center; */
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
  width: ${(props) => props.width};
`;

export const DetailBox = styled.div<{ width: string; height: string; background: string }>`
  display: flex;
  overflow: hidden;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: space-around;
  padding: 0px 20px 0px;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
  width: ${(props) => props.width};
  border: 1px solid ${colors.default};
`;

export const EpicContainer = styled.div<{ width: string; height: string }>`
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  /* align-items: center; */
  overflow-y: auto;
  margin: 1%;
  background-color: ${colors.white};
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  box-sizing: border-box;
  &::-webkit-scrollbar {
    width: 12px;
  }
  &::-webkit-scrollbar-track {
    background: #f1f1f1;
    border-radius: 10px;
  }
  &::-webkit-scrollbar-thumb {
    background: #888;
    border-radius: 10px;
  }
  &::-webkit-scrollbar-thumb:hover {
    background: #555;
  }
`;

export const EpicItem = styled.div<{ width: string; height: string; background: string }>`
  display: flex;
  justify-content: space-around;
  align-items: center;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
  width: ${(props) => props.width};
  border-bottom: 2px solid ${colors.default};
`;

export const TaskInfoStyle = styled.p`
  display: flex;
  width: 18%;
  padding: 0% 2% 0%;
  font-size: 15px;
`;

export const Photo = styled.div<{
  width: string;
  height: string;
  background: string;
  imgurl: string;
  borderradius: string;
}>`
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  background-color: ${(props) => props.background} !important;
  height: ${(props) => props.height};
  width: ${(props) => props.width};
  margin: 0% 3% 0% 0%;
  border-radius: ${(props) => props.borderradius};
  background: url(${(props) => props.imgurl}) no-repeat center center;
  background-size: cover;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
`;

export const ProfileBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  height: 60px;
  margin: 0% 4% 0% 0%;
`;

export const Segment = styled.div`
  flex: 1;
`;
