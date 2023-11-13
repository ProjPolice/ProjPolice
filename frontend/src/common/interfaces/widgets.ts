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
