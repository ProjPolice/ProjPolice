import dayjs from 'dayjs';

export const getTimelineProgress = (startDate: string, endDate: string, startOfWeek: string) => {
  const width = (dayjs(endDate).diff(dayjs(startOfWeek), 'day') / 6) * 100;
  const empty = (dayjs(startDate).diff(dayjs(startOfWeek), 'day') / 6) * 100;

  return { width: width, empty: (empty / width) * 100 };
};
