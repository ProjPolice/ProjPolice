import { EpicData } from '@api/epic';
import { MemberData, ProjectData } from '@api/project';
import { TasksData } from '@api/task';
import { atom } from 'recoil';

export const selectedIndexState = atom({
  key: 'selectedIndexState',
  default: -1,
});

export const projectDataState = atom<ProjectData>({
  key: 'projectDataState',
  default: {
    id: 0,
    name: '',
    description: '',
    startDate: '',
    endDate: '',
    owner: {
      id: 0,
      name: '',
    },
  },
});

const defaultProjectMember: MemberData[] = [];

export const projectMemberState = atom({
  key: 'projectMemberState',
  default: defaultProjectMember,
});

const defaultEpicData: EpicData[] = [];

export const epicDataState = atom({
  key: 'epicDataState',
  default: defaultEpicData,
});

const defaultTaskData: TasksData[] = [];

export const taskDataState = atom({
  key: 'taskDataState',
  default: defaultTaskData,
});

export const currentProjectOwner = atom({
  key: 'currentProjectOwner',
  default: -1,
});
