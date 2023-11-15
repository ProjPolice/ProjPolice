import { Global } from '@emotion/react';
import { globalStyle } from '@assets/design/globalStyles';
import { Page } from '@assets/design/globalStyles';
import { CenterContainer } from './IntroStyle';
import { colors } from '@assets/design/colors';
import { Container } from './IntroStyle';
import { Photo } from './IntroStyle';
import { Button } from './IntroStyle';
import { Ptext } from './IntroStyle';
// import ProjectPoliceIcon from '@assets/images/ProjPoliceIcon.png';
import Img1 from '@assets/images/img1.png';
import Img2 from '@assets/images/img2.png';
import Img3 from '@assets/images/img3.png';

function Intro() {
  return (
    <Page>
      <Global styles={globalStyle} />
      {/* 전체 컨테이너 */}
      <CenterContainer background={colors.light}>
        {/* 섹션1 */}
        <Container
          width={'100%'}
          height={'60%'}
          background={colors.default}
          flexdirection=""
          style={{ padding: '0% 2% 0%' }}
        >
          {/* 섹션 1 - Left (문구, 버튼) */}
          <Container
            width={'60%'}
            height={'100%'}
            background={''}
            flexdirection="column"
            style={{ padding: '0% 7% 0%' }}
          >
            <h3 style={{ marginBottom: '5%' }}>프로폴리스 서비스 소개</h3>
            <Ptext style={{ fontSize: '2.2rem', margin: '1% 0% 4%' }}>
              프로젝트 입문자를 위한, 쉽고 편한 작업 관리 서비스
            </Ptext>
            <Ptext style={{ fontSize: '1.4rem', lineHeight: '1.6' }}>
              협업과 프로젝트 관리가 처음이신가요? 걱정 마세요. 프로폴리스가 도와드립니다.
              <br /> 한 눈에 들어오는 편안한 구성과 사용자 친화적인 설계로, 인터페이스를 쉽게 조작할 수 있도록
              하였습니다. 프로젝트와 일정을 등록하고 팀원을 초대하여, 모두가 함께 공유하고 열람할 수 있도록 하였습니다.
              누가 무엇을 하고 있는지 프로젝트의 현황을 한 눈에 볼 수 있으며, 내가 해야할 작업들을 임박한 순서대로
              보여주어 계획 수립을 도와드립니다. 작업에 대한 파일을 업로드 및 등록하여 필요한 문서나 파일을 찾는 데
              시간을 낭비하지 않도록 도와드립니다.
            </Ptext>
            <Ptext style={{ fontSize: '2rem', margin: '4% 0% 1%' }}>
              만능 프로젝트 관리자! 프로젝트 폴리스와 함께하세요.
            </Ptext>
            <Button width={'160px'} height={'45px'} fontsize="14px">
              서비스 체험하기
            </Button>
          </Container>
          {/* 섹션 1- Right (이미지) */}
          <Container width={'40%'} height={'100%'} background={''} flexdirection="">
            <Photo width={'90%'} background={''} imgurl={Img1} />
          </Container>
        </Container>

        {/* 섹션2 */}
        <Container
          width={'100%'}
          height={'45%'}
          background={colors.board1}
          flexdirection=""
          style={{ padding: '0% 2% 0%' }}
        >
          {/* 섹션 2- Left (이미지) */}
          <Container width={'40%'} height={'100%'} background={''} flexdirection="">
            <Photo width={'70%'} background={''} imgurl={Img1} />
          </Container>
          {/* 섹션 2 - Right (문구) */}
          <Container width={'60%'} height={'100%'} background={''} flexdirection="column">
            <h3 style={{ marginBottom: '5%' }}>일정과 현황을 한 눈에 </h3>
            <Ptext>복잡한 일정표는 이제 그만. 프로폴리스의 직관적인 인터페이스와 함께하세요.</Ptext>
            <Ptext>[프로젝트 - 할 일 - 작업] 단위로 일감을 쪼개어 관리할 수 있도록 도와드립니다.</Ptext>
            <Ptext>체계적인 일감 관리의 재미를 느껴 보세요!</Ptext>
          </Container>
        </Container>

        {/* 섹션 3 */}
        <Container
          width={'100%'}
          height={'45%'}
          background={colors.board2}
          flexdirection=""
          style={{ padding: '0% 2% 0%' }}
        >
          {/* 섹션 3 - Left (문구) */}
          <Container width={'60%'} height={'100%'} background={''} flexdirection="column">
            <h3 style={{ marginBottom: '5%' }}>칸반보드 형식으로 쉽고 빠르게</h3>
            <Ptext>드래그 앤 드랍 형식의 칸반보드 형식을 차용하였습니다.</Ptext>
            <Ptext>작업의 시작부터 진행, 완료까지. 상태를 간편하게 관리할 수 있습니다</Ptext>
            <Ptext>마우스 하나만으로 작업 현황을 관리해 보세요!</Ptext>
          </Container>
          {/* 섹션 3- Right (이미지) */}
          <Container width={'40%'} height={'100%'} background={''} flexdirection="">
            <Photo width={'80%'} background={''} imgurl={Img3} />
          </Container>
        </Container>

        {/* 섹션 4 */}
        <Container
          width={'100%'}
          height={'45%'}
          background={colors.board3}
          flexdirection=""
          style={{ padding: '0% 2% 0%' }}
        >
          {/* 섹션 4- Left (이미지) */}
          <Container width={'40%'} height={'100%'} background={''} flexdirection="">
            <Photo width={'80%'} background={''} imgurl={Img2} />
          </Container>
          {/* 섹션 4 - Right (문구) */}
          <Container width={'60%'} height={'100%'} background={''} flexdirection="column">
            <h3 style={{ marginBottom: '5%' }}> 파일 관리까지 체계적으로</h3>
            <Ptext>작업에 필요한 파일을 찾는 데 시간을 낭비하지 마세요.</Ptext>
            <Ptext>작업에 대한 정보와 함께, 업로드 된 최신 파일을 보여드립니다.</Ptext>
            <Ptext>진행된 파일을 바로 업로드하고 팀원들과 공유해 보세요!</Ptext>
          </Container>
        </Container>
        <Container
          width={'100%'}
          height={'40%'}
          background={colors.default}
          flexdirection="column"
          style={{ justifyContent: 'space-evenly', padding: '3% 0% 2%' }}
        >
          <Container width={'70%'} height={''} background={colors.default} flexdirection="column">
            <h3 style={{ marginBottom: '3%' }}>협업의 새로운 시작, 프로폴리스가 도와드립니다.</h3>
            <Ptext>
              협업은 이제 더 이상 부담스러운 일이 아닙니다. 프로폴리스와 함께라면 프로젝트 관리는 단순하고, 명료하며,
              효과적입니다.
            </Ptext>
            <Ptext>
              초보자부터 전문가까지, 모두가 만족할 수 있는 서비스를 경험해 보세요. 지금 바로 팀원들과 시작해 보세요!
            </Ptext>
          </Container>
          <Button width={'300px'} height={'60px'} fontsize="1.6rem" style={{ marginTop: '4%' }}>
            지금 회원가입하러 가기
          </Button>
          <Ptext>
            Copyright (c) 2023. Team 저 무임승차할게요ㅋㅋ 그럼 선배님 이름도 뺄게요 ㅎㅎ. All rights reserved
          </Ptext>
        </Container>
      </CenterContainer>
    </Page>
  );
}

export default Intro;
