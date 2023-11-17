import { ChangeEvent, Dispatch, SetStateAction, useState } from 'react';

export const useNumberInput = (
  initialValue?: number,
): [number, (event: ChangeEvent<HTMLInputElement>) => void, Dispatch<SetStateAction<number>>] => {
  const [value, setValue] = useState(initialValue ? initialValue : 0);

  const handleValue = (event: ChangeEvent<HTMLInputElement>) => {
    if (/^\d+$/.test(event.target.value)) {
      setValue(Number(event.target.value));
    } else {
      alert('숫자만 입력이 가능합니다.');
    }
  };

  return [value, handleValue, setValue];
};
