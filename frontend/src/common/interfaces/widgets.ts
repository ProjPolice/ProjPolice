export interface ProjPoliceButtonProps {
  width: number;
  height: number;
  color?: string;
  context: string;
  onClick: () => void;
  type?: string;
}

export interface ModalProps {
  visible: boolean;
  handleVisible: () => void;
}

export interface EpicModalProps extends ModalProps {
  projectId: number;
}

export interface TaskModalProps extends EpicModalProps {
  epicId: number;
}

export interface UploadFileProps extends ModalProps {
  taskId: number;
}

export interface FileModalProps extends ModalProps {
  taskId: number;
}

export interface fileIconProps {
  extension: string | undefined;
  onClick: () => void;
  name?: string;
  size?: number;
}
