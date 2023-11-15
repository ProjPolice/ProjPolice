export const parseStatus = (status: string) => {
  if (status === 'TODO') {
    return '해야할 일';
  } else if (status === 'PROCEEDING') {
    return '진행 중';
  } else if (status === 'DONE') {
    return '완료';
  } else {
    return '기타';
  }
};
