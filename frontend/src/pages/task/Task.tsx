import { Page } from './TaskStyle';
import Board from './Board/Board';
// import styled from '@emotion/styled';

function Task() {
  return (
    <Page>
      <div style={{ margin: '1% 0% 1% 10%', alignSelf: 'start' }}>
        <h3>내 작업 관리</h3>
      </div>
      <Board />
    </Page>
  );
}

export default Task;

// const H3 = styled.h3``;
