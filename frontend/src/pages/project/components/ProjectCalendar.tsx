import { colors } from '@assets/design/colors';
import { Container, ContainerContent, CalendarDayBox, ContainerNav, PositionContainer, TimeLineContainer, DayNav, DayContainer } from '@project/ProjectStyle';

function ProjectCalendar() {
  return (
    <Container width={"97%"} height={'95%'} style={{boxShadow:"none", borderRadius:"0px", border:"none"}}>
      <ContainerNav height={'15%'} background={colors.primary} style={{justifyContent:"center"}}>
        <p style={{color:"white"}}>YYYY년 M월 W주차</p> 
      </ContainerNav>
      {/* <PositionContainer width={'100%'} height={'85%'}> */}
        <ContainerContent width={'100%'} height={'85%'} background={colors.light}>
          <CalendarDayBox>
            <DayNav width={'100%'} height={'15%'} background=""> 
              <p style={{fontSize:"12px"}}>MM.DD</p> <p style= {{fontSize:"12px"}}>(일)</p>
            </DayNav>
            {/* <DayContainer width={'100%'} height={'100%'} background="pink"/> */}
            {/* <TimeLineContainer width={'100%'} height={'100%'} background="pink"/> */}

          </CalendarDayBox>
        </ContainerContent>
      {/* </PositionContainer> */}
    </Container>
  );
}

export default ProjectCalendar;
