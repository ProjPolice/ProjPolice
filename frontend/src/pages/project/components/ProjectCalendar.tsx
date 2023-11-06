import { Container } from "@project/ProjectStyle";
import { ContainerNav } from "@project/ProjectStyle";
import { ContainerContent } from "@project/ProjectStyle";


function ProjectCalendar() {
  return (
    <Container width={"97%"} height={'95%'} style={{boxShadow:"none", borderRadius:"0px", border:"none"}}>
      <ContainerNav height={'15%'} background="#2391ee" style={{justifyContent:"center"}}>
        <p style={{color:"white"}}>YYYY년 M월 W주차</p> 
      </ContainerNav>
      {/* <ContainerNav height={'0.5%'} background="#d8d8d8"/> */}
      <ContainerContent height={'85%'} background=""/>
    </Container>
  );
}

export default ProjectCalendar;
