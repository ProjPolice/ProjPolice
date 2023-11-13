export interface ProjPoliceButtonProps {
  width: number;
  height: number;
  color?: string;
  context: string;
  onClick: () => void;
}

export interface ModalProps {
  visible: boolean;
  handleVisible: () => void;
}
