import { fileIconProps } from '@interfaces/widgets';
import { downloadFile } from '@utils/downloadFile';
import excel from '@assets/icons/fileIcons/excel.png';
import powerpoint from '@assets/icons/fileIcons/powerpoint.png';
import word from '@assets/icons/fileIcons/word.png';
import image from '@assets/icons/fileIcons/image.png';
import unknown from '@assets/icons/fileIcons/unknown.png';

const FileIcon = ({ extension, fileId }: fileIconProps) => {
  let src = '';
  switch (extension) {
    case 'xls':
      src = excel;
      break;
    case 'xlsx':
      src = excel;
      break;
    case 'ppt':
      src = powerpoint;
      break;
    case 'pptx':
      src = powerpoint;
      break;
    case 'doc':
      src = word;
      break;
    case 'docx':
      src = word;
      break;
    case 'jpg':
      src = image;
      break;
    case 'jpeg':
      src = image;
      break;
    case 'png':
      src = image;
      break;
    default:
      src = unknown;
      break;
  }
  return <img src={src} alt={extension} width={30} height={30} onClick={() => downloadFile(fileId)} />;
};

export default FileIcon;
