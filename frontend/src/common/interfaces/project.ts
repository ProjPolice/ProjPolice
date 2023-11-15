import { MemberData } from '@api/project';

export interface ProjectIdProps {
  projectId: number;
}

export interface TimelineListProps {
  projectId: number;
  startDate: string;
  endDate: string;
}

export interface TaskTimelineListProps {
  id: number;
  name: string;
  startDate: string;
  endDate: string;
  epicStart: string;
  index: number;
}

export interface EpicDetailProps {
  projectId: number;
  epicId: number;
}

export interface MemberListProps {
  members: MemberData[];
}
