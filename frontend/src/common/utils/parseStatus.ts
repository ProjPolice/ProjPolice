export const parseStatus = (status: string | undefined) => {
  switch (status) {
    case 'TODO':
      return '해야할 일';
    case 'PROCEEDING':
      return '진행 중';
    case 'DONE':
      return '완료';
    default:
      return '기타';
  }
};
