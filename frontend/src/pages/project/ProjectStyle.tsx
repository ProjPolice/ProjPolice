import styled from '@emotion/styled';

export const Page = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100vh;
  align-items: center;
`;

export const CenterContainer = styled.div<{ background: string; }>`
  display: flex;
  flex-direction: column;
  width: 70%;
  height: 100vh;
  align-items: center;
  //MUST BE DELETED AFTER
  background-color: ${(props) => props.background};
`;

export const ProjectSection = styled.div<{ height: string, background: string; }>`
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  width: 100%;
  /* margin : 0% 10% 0% 10%; */
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
`;


export const Container = styled.div<{ width: string, height: string, background: string; }>`
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  overflow: hidden;
  margin: 0%;
  background-color: ${(props) => props.background};
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  border-radius: 10px;
  border: 1px solid #d8d8d8;
  box-sizing: border-box;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const SectionHeader = styled.div<{ height: string, background: string, alignitems: string; }>`
  display: flex;
  /* justify-content: space-evenly; */
  background-color: ${(props) => props.background};
  width: 90%;
  height: ${(props) => props.height};
  align-items: ${(props) => props.alignitems};
`;

export const ContainerNav = styled.div<{ height: string, background: string }>`
  display: flex;  
  justify-content: space-between;
  background-color: ${(props)=> props.background};
  height: ${(props) => props.height};
  width: 100% - 35px;
  padding: 0% 35px;
  align-items: center;
`;

export const ContainerContent = styled.div<{height: string;}>`
  display: flex;  
  height: ${(props) => props.height};
  width: 100%;
  justify-content: center;
  align-items: center;
`;

export const Segment = styled.div`
  flex: 1;
`;
