import { useEffect, useState } from 'react';

import ProjectItem from './ProjectList/ProjectItem';
import { ProjectBoxContainer, ProjectContainer, TextContainer } from '@main/MainStyle';

import LeftArrow from '@assets/icons/LeftArrow.png';
import RightArrow from '@assets/icons/RightArrow.png';
import user, { Projects } from '@api/user';
import ProjPoliceButton from '@widgets/buttons/ProjPoliceButton';
import CreateProjectkModal from '@widgets/modals/CreateProjectModal';

function ProjectList() {
  const [items, setItems] = useState<Projects[]>([]);
  const [visible, setVisible] = useState(false);

  useEffect(() => {
    user.projects().then((response) => {
      setItems(response.data.projects);
    });
  }, []);

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
  const visibleItems = items.slice(startIndex, endIndex);

  const handleModalVisible = () => {
    setVisible(!visible);
  };

  return (
    <ProjectContainer>
      <CreateProjectkModal visible={visible} handleVisible={handleModalVisible} />
      <TextContainer>
        <div style={{ display: 'flex', alignItems: 'center', justifyItems: 'center', width: '100%', gap: '1%' }}>
          <h4>프로젝트</h4>
          <ProjPoliceButton width={55} height={30} context="+ 추가" onClick={handleModalVisible} />
        </div>
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
      </TextContainer>
      <ProjectBoxContainer>
        {visibleItems.map((project, index) => (
          <ProjectItem {...project} key={index} />
        ))}
      </ProjectBoxContainer>
    </ProjectContainer>
  );
}

export default ProjectList;
