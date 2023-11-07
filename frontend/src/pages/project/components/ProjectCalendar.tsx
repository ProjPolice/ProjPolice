import { colors } from '@assets/design/colors';
import { Container } from '@project/ProjectStyle';
import { CalendarDayBox } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { PositionContainer } from '@project/ProjectStyle';
import { DayNav } from '@project/ProjectStyle';
import { DayContainer } from '@project/ProjectStyle';

import TimelineList from './TimelineList';

function ProjectCalendar() {
  return (
    <Container width={'97%'} height={'95%'} style={{ boxShadow: 'none', borderRadius: '0px', border: 'none' }}>
      <ContainerNav height={'15%'} background={colors.primary} style={{ justifyContent: 'center' }}>
        <p style={{ color: 'white' }}>YYYY년 M월 W주차</p>
      </ContainerNav>
      <PositionContainer width={'100%'} height={'85%'}>
        <TimelineList />
        <DayContainer width={'100%'} height={'100%'} background={colors.light}>
          <CalendarDayBox>
            <DayNav width={'100%'} height={'15%'} background="">
              <p style={{ fontSize: '12px' }}>MM.DD</p> <p style={{ fontSize: '12px' }}>(일)</p>
            </DayNav>
          </CalendarDayBox>
          <CalendarDayBox>
            <DayNav width={'100%'} height={'15%'} background="">
              <p style={{ fontSize: '12px' }}>MM.DD</p> <p style={{ fontSize: '12px' }}>(일)</p>
            </DayNav>
          </CalendarDayBox>
          <CalendarDayBox>
            <DayNav width={'100%'} height={'15%'} background="">
              <p style={{ fontSize: '12px' }}>MM.DD</p> <p style={{ fontSize: '12px' }}>(일)</p>
            </DayNav>
          </CalendarDayBox>
          <CalendarDayBox>
            <DayNav width={'100%'} height={'15%'} background="">
              <p style={{ fontSize: '12px' }}>MM.DD</p> <p style={{ fontSize: '12px' }}>(일)</p>
            </DayNav>
          </CalendarDayBox>
          <CalendarDayBox>
            <DayNav width={'100%'} height={'15%'} background="">
              <p style={{ fontSize: '12px' }}>MM.DD</p> <p style={{ fontSize: '12px' }}>(일)</p>
            </DayNav>
          </CalendarDayBox>
          <CalendarDayBox>
            <DayNav width={'100%'} height={'15%'} background="">
              <p style={{ fontSize: '12px' }}>MM.DD</p> <p style={{ fontSize: '12px' }}>(일)</p>
            </DayNav>
          </CalendarDayBox>
        </DayContainer>
      </PositionContainer>
    </Container>
  );
}

export default ProjectCalendar;
