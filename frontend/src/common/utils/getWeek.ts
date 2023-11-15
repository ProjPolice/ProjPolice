import dayjs from 'dayjs';

export const getWeek = () => {
  const today = dayjs();
  const current = today.date();
  const first = today.set('date', 1).day();
  const week = Math.ceil((current + first) / 7);

  return week;
};
