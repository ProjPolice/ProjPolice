import file from '@api/file';

export const downloadFile = (fileId: number) => {
  file
    .download(fileId)
    .then((response) => {
      const a = document.createElement('a');
      a.href = URL.createObjectURL(response.data);
      const contentDisposition = response.headers['content-disposition'];
      const [input] = contentDisposition.split(';').filter((c: string) => c.includes('filename*'));
      const name = parseFileName(input);
      console.log(name);
      a.download = name;
      a.click();
    })
    .catch((error) => {
      console.log(error);
      alert('파일 다운로드에 실패하였습니다');
    });
};

const parseFileName = (input: string) => {
  // '%'로 시작하는 부분 찾기
  const match = input.match(/%[0-9A-Fa-f]{2}/g);
  const extension = input.slice(input.lastIndexOf('.'));

  if (match) {
    // '%' 다음의 두 자리 16진수를 유니코드 문자로 변환하여 배열 생성
    const name = decodeURIComponent(match.join(''));
    return name + extension;
  }
  return '';
};
