import { FileModalProps } from '@interfaces/widgets';
import { css } from '@emotion/react';
import { colors } from '@assets/design/colors';
import file, { FileData } from '@api/file';
import { useEffect, useState } from 'react';
import FileIcon from '@widgets/FileIcon';
import { downloadFile } from '@utils/downloadFile';
import dayjs from 'dayjs';

function FileModal({ visible, handleVisible, taskId }: FileModalProps) {
  const [files, setFiles] = useState<FileData[]>([]);
  useEffect(() => {
    if (taskId !== -1 && visible) {
      file
        .data({ taskId })
        .then((response) => {
          setFiles(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [taskId, visible]);
  return (
    <>
      {visible && (
        <div
          css={css`
            width: 50%;
            height: 25%;
            z-index: 100;
            position: absolute;
            top: 37.5%;
            left: 25%;
            background: linear-gradient(to bottom, ${colors.primary} 20%, ${colors.white} 0%);
            box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
            display: flex;
            border-radius: 10px;
            align-items: center;
          `}
          onClick={handleVisible}
        >
          {files.map((file, index) => (
            <div
              css={css`
                display: flex;
                flex-direction: column;
                aspect-ratio: 1;
                height: 30%;
                justify-content: space-evenly;
                align-items: center;
              `}
              key={index}
            >
              <p style={{ fontSize: 18 }}>V.{file.version}</p>
              <FileIcon extension={file.extension} onClick={() => downloadFile(file.id)} size={50} />
              <p style={{ fontSize: 10, textAlign: 'center' }}>{dayjs(file.createdAt).format('YYYY.MM.DD HH:mm:ss')}</p>
            </div>
          ))}
        </div>
      )}
    </>
  );
}

export default FileModal;
