import React, { useState } from 'react';

function ProjectCreateModal({ isOpen, onClose, onCreate }) {
  const [projectName, setProjectName] = useState('');
  const [projectContent, setProjectContent] = useState('');
  const [projectDate, setProjectDate] = useState('');

  const handleProjectNameChange = (e) => {
    setProjectName(e.target.value);
  };

  const handleProjectContentChange = (e) => {
    setProjectContent(e.target.value);
  };

  const handleProjectDateChange = (e) => {
    setProjectDate(e.target.value);
  };

  const handleCreateProject = () => {
    onCreate({
      name: projectName,
      content: projectContent,
      date: projectDate,
    });

    // 입력 필드 초기화
    setProjectName('');
    setProjectContent('');
    setProjectDate('');
  };

  return (
    isOpen && (
      <div className="modal">
        <div className="modal-content">
          <input
            type="text"
            placeholder="프로젝트 이름"
            value={projectName}
            onChange={handleProjectNameChange}
          />
          <textarea
            placeholder="프로젝트 내용"
            value={projectContent}
            onChange={handleProjectContentChange}
          />
          <input
            type="date"
            value={projectDate}
            onChange={handleProjectDateChange}
          />
          <button onClick={handleCreateProject}>생성</button>
          <button onClick={onClose}>취소</button>
        </div>
      </div>
    )
  );
}

export default ProjectCreateModal;