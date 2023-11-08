export interface HeaderBoxProps {
  count?: number;
  name?: string;
  icon?: string;
  backgroundColor?: string;
}

export interface TaskItemProps {
  backgroundColor: string;
  projectname: string;
  membercount: number;
}

export interface ProjectItemProps {
  backgroundColor: string;
  title: string;
  members: string[];
  tasks: string[];
  projectId: number;
}

export interface BoardItemProps {
  name?: string;
  backgroundColor: string;
}
