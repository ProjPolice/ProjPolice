import { useEffect, useState } from 'react';

import ProjectItem from './ProjectList/ProjectItem';
import { ProjectBoxContainer, ProjectContainer, TextContainer } from '@main/MainStyle';
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
          <ProjPoliceButton width={55} height={25} context="+ 추가" onClick={handleModalVisible} />
        </div>
        <div style={{ display: 'flex', gap: '3%' }}>
          {currentPage > 0 && (
            <ProjPoliceButton width={30} height={30} context="←" onClick={handlePrevPage} type="bold" />
          )}
          {endIndex < items.length && (
            <ProjPoliceButton width={30} height={30} context="→" onClick={handleNextPage} type="bold" />
          )}
        </div>
      </TextContainer>
      <ProjectBoxContainer>
        {visibleItems.map((project, index) => (
          <ProjectItem {...project} key={index} index={index} />
        ))}
      </ProjectBoxContainer>
    </ProjectContainer>
  );
}

export default ProjectList;
