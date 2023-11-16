import file from '@api/file';

export const downloadFile = (fileId: number) => {
  file
    .download(fileId)
    .then((response) => {
      const blob = new Blob([response.data], { type: response.headers['content-type'] });
      const a = document.createElement('a');
      a.href = URL.createObjectURL(blob);
      console.log(response.headers);
      a.click();
    })
    .catch((error) => {
      console.log(error);
      alert('파일 다운로드에 실패하였습니다');
    });
};
