import { useState } from 'react';

import ProjectItem from './ProjectList/ProjectItem';
import { colors } from '@assets/design/colors';
import { ProjectBoxContainer, ProjectContainer, TextContainer } from '@main/MainStyle';

import AddButton from '../../../common/assets/icons/AddButton.png';
import LeftArrow from '../../../common/assets/icons/LeftArrow.png';
import RightArrow from '../../../common/assets/icons/RightArrow.png';

function ProjectList() {
  const projectItems = [
    {
      title: '프로젝트 1',
      members: ['철수', '영희', '민지'],
      tasks: ['할 일 1', '할 일 2', '할 일 3', '할 일 4'],
      backgroundColor: colors.yellow,
    },
    {
      title: '프로젝트 2',
      members: ['철수', '영희', '민지'],
      tasks: ['할 일 1', '할 일 2', '할 일 3', '할 일 4'],
      backgroundColor: colors.blue,
    },
    {
      title: '프로젝트 3',
      members: ['철수', '영희', '민지'],
      tasks: ['할 일 1', '할 일 2', '할 일 3', '할 일 4', '할 일 5', '할 일 6'],
      backgroundColor: colors.black,
    },
    {
      title: '프로젝트 4',
      members: ['철수', '영희', '민지'],
      tasks: ['할 일 1', '할 일 2', '할 일 3', '할 일 4'],
      backgroundColor: colors.yellow,
    },
    {
      title: '프로젝트 5',
      members: ['철수', '영희', '민지'],
      tasks: ['할 일 1', '할 일 2', '할 일 3', '할 일 4'],
      backgroundColor: colors.blue,
    },
    {
      title: '프로젝트 6',
      members: ['철수', '영희', '민지'],
      tasks: ['할 일 1', '할 일 2', '할 일 3', '할 일 4', '할 일 5', '할 일 6'],
      backgroundColor: colors.black,
    },
    {
      title: '프로젝트 7',
      members: ['철수', '영희', '민지'],
      tasks: ['할 일 1', '할 일 2', '할 일 3', '할 일 4'],
      backgroundColor: colors.yellow,
    },
    {
      title: '프로젝트 8',
      members: ['철수', '영희', '민지'],
      tasks: ['할 일 1', '할 일 2', '할 일 3', '할 일 4'],
      backgroundColor: colors.blue,
    },
    {
      title: '프로젝트 9',
      members: ['철수', '영희', '민지'],
      tasks: ['할 일 1', '할 일 2', '할 일 3', '할 일 4', '할 일 5', '할 일 6'],
      backgroundColor: colors.black,
    },
  ];

  const itemsPerPage = 3; // 한 페이지에 보여질 아이템 수
  const [currentPage, setCurrentPage] = useState(0);

  const handleNextPage = () => {
    setCurrentPage((prevPage) => prevPage + 1);
  };

  const handlePrevPage = () => {
    setCurrentPage((prevPage) => prevPage - 1);
  };

  const startIndex = currentPage * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const visibleItems = projectItems.slice(startIndex, endIndex);

  return (
    <ProjectContainer>
      <TextContainer>
        <div style={{ display: 'flex', alignItems: 'center', justifyItems: 'center' }}>
          <h4>프로젝트</h4>
          <button style={{ border: 'none', background: 'none', cursor: 'pointer' }}>
            <img src={AddButton} style={{ width: '70%', height: '100%' }}></img>
          </button>
        </div>
        <p>
          <div>
            {currentPage > 0 && (
              <button style={{ border: 'none', background: 'none', cursor: 'pointer' }} onClick={handlePrevPage}>
                <img src={LeftArrow} style={{ width: '70%', height: '100%' }}></img>
              </button>
            )}
            {endIndex < projectItems.length && (
              <button style={{ border: 'none', background: 'none', cursor: 'pointer' }} onClick={handleNextPage}>
                <img src={RightArrow} style={{ width: '70%', height: '50%' }}></img>
              </button>
            )}
          </div>
        </p>
      </TextContainer>
      <ProjectBoxContainer>
        {visibleItems.map((project, index) => (
          <ProjectItem {...project} projectId={index} key={index} />
        ))}
      </ProjectBoxContainer>
    </ProjectContainer>
  );
}

export default ProjectList;
