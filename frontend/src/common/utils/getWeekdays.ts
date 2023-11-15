import dayjs from 'dayjs';

export const getWeekdays = (year: number, month: number, week: number) => {
  const first = dayjs().year(year).month(month).date(1).day();
  let thisWeekMonday = (week - 1) * 7 - (first - 2);
  const dayList = ['월', '화', '수', '목', '금', '토'];

  const weekdays = [];

  for (const i of dayList) {
    weekdays.push({ date: thisWeekMonday, day: i });
    thisWeekMonday = thisWeekMonday + 1;
  }
  return weekdays;
};
