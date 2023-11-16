import file from '@api/file';

export const downloadFile = (fileId: number) => {
  file
    .download(fileId)
    .then((response) => {
      const a = document.createElement('a');
      a.href = URL.createObjectURL(response.data);
      a.download = response.headers['content-disposition'];
      a.click();
      console.log(response);
    })
    .catch((error) => {
      console.log(error);
      alert('파일 다운로드에 실패하였습니다');
    });
};
