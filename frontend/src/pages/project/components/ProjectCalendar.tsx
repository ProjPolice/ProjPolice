import { colors } from '@assets/design/colors';
import { Container } from '@project/ProjectStyle';
import { CalendarDayBox } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { PositionContainer } from '@project/ProjectStyle';
import { DayNav } from '@project/ProjectStyle';
import { DayContainer } from '@project/ProjectStyle';

import { getWeek } from '@utils/getWeek';
import dayjs from 'dayjs';
import { useState } from 'react';
import { getWeekdays } from '@utils/getWeekdays';
import TimelineList from './TimelineList';
import { ProjectIdProps } from '@interfaces/project';

function ProjectCalendar({ projectId }: ProjectIdProps) {
  const [year] = useState(dayjs().year());
  const [month] = useState(dayjs().month());
  const [week] = useState(getWeek());

  const weekdays = getWeekdays(year, month, week);

  return (
    <Container width={'97%'} height={'95%'} style={{ boxShadow: 'none', borderRadius: '0px', border: 'none' }}>
      <ContainerNav height={'15%'} background={colors.primary} style={{ justifyContent: 'center' }}>
        <p style={{ color: 'white' }}>
          {year} 년 {month + 1} 월 {week} 주차
        </p>
      </ContainerNav>
      <PositionContainer width={'100%'} height={'85%'}>
        <TimelineList
          startDate={`${year}-${month + 1}-${weekdays[0].date}`}
          endDate={`${year}-${month + 1}-${weekdays[5].date}`}
          projectId={projectId}
        />
        <DayContainer width={'100%'} height={'100%'} background={colors.light}>
          {weekdays.map((day, index) => (
            <CalendarDayBox key={index}>
              <DayNav
                width={'100%'}
                height={'15%'}
                background=""
                isToday={day.date === dayjs().date() && month === dayjs().month()}
              >
                <p style={{ fontSize: '12px' }}>
                  {month + 1}.{day.date}
                </p>
                <p style={{ fontSize: '12px' }}>{day.day}</p>
              </DayNav>
            </CalendarDayBox>
          ))}
        </DayContainer>
      </PositionContainer>
    </Container>
  );
}

export default ProjectCalendar;
