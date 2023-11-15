import { EpicData } from '@api/epic';
import { TasksData } from '@api/task';
import { atom } from 'recoil';

export const selectedIndexState = atom({
  key: 'selectedIndexState',
  default: -1,
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
